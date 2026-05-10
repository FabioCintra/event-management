package io.github.fabiocintra.event_management.event.model.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationRequest(
        @NotBlank(message = "Campo Obrigatorio!")
        @Size(max=80, message = "Campo deve ter no maximo 80 caracteres!")
        String country,

        @NotBlank(message = "Campo Obrigatorio!")
        @Size(max=100, message = "Campo deve ter no maximo 100 caracteres!")
        String city,

        @NotBlank(message = "Campo Obrigatorio!")
        @Size(max=150, message = "Campo deve ter no maximo 150 caracteres!")
        String street,

        @Size(min=10, max=200, message = "Campo deve ter de 10 a 200 caracteres!")
        String complement,

        Integer number
) {
}
