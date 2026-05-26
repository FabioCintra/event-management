package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.event.EventService;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.order.OrderService;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.exceptions.MethodErrorException;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidate userValidate;
    private final EventService eventService;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        userValidate.userIsValid(user);
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(String id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        User user = userOptional.get();

        //buscar seus eventos
        List<Event> events = eventService.findAllEventByOrganizer(user);
        user.setEvents(events);

        //buscando orders
        List<Order> orders = orderService.findOrderAllByAttendee(user);
        user.setOrders(orders);

        return user;
    }

    public void updateUser(User user) {
        if(user.getId() == null){
            throw new MethodErrorException("Cannot update an user,that is not registered!");
        }
        userValidate.userIsValid(user);
        User userUpdated = userRepository.findById(user.getId()).get();
        userUpdated.setName(user.getName());
        userUpdated.setRole(user.getRole());
        userUpdated.setEmail(user.getEmail());
        userRepository.save(userUpdated);
    }
}
