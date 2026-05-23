package io.github.fabiocintra.event_management.ticket;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.github.fabiocintra.event_management.order.OrderRepository;
import io.github.fabiocintra.event_management.order.OrderService;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.ticket.model.Ticket;
import io.github.fabiocintra.event_management.ticket.model.TicketStatus;
import io.github.fabiocintra.event_management.ticket_type.TicketTypeService;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;
    private final OrderRepository orderRepository;
    private final TicketTypeService ticketTypeService;

    public void createTicket(Order order,TicketType ticketType) {
        Ticket ticket = new Ticket();
        String qrCodeString = "data:image/png;base64," + createQRCodeBase64();

        ticket.setOrder(order);
        ticket.setTicketType(ticketType);
        ticket.setStatus(TicketStatus.ISSUED);
        ticket.setQrCode(qrCodeString);

        repository.save(ticket);
    }

    public List<Ticket> findTicket(String orderId, String ticketTypeId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()){
            throw new NotFoundException("Order not found");
        }
        Order order = orderOptional.get();
        TicketType ticketType = ticketTypeService.findTicketTypeById(ticketTypeId);
        List<Ticket> tickets = repository.findByOrderAndTicketType(order, ticketType);
        if (tickets.isEmpty()){
            throw new NotFoundException("Ticket not found!");
        }
        return tickets;
    }

    private String createQRCodeBase64() {
        try {
            // 1. Criar a matriz do QR Code
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(
                    "https://blog.esportudo.com/hs-fs/hubfs/Top-10-as-comemoracoes-mais-criativas-no-futebol-nos-ultimos-tempos-cristiano-ronaldo.jpg?width=1536&name=Top-10-as-comemoracoes-mais-criativas-no-futebol-nos-ultimos-tempos-cristiano-ronaldo.jpg",
                    BarcodeFormat.QR_CODE,
                    300,
                    300);

            // 2. Converter a matriz para um array de bytes (como imagem PNG)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
            byte[] qrCodeBytes = baos.toByteArray();

            // 3. Converter os bytes para uma String Base64
            return Base64.getEncoder().encodeToString(qrCodeBytes);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar QR Code", e);
        }
    }

}
