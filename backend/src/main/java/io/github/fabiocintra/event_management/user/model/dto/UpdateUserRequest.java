package io.github.fabiocintra.event_management.user.model.dto;

import io.github.fabiocintra.event_management.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateUserRequest(
        @NotBlank(message = "Campo obrigatorio!")
        @Size(min=3, max=300, message = "Deve conter entre 3 a 300 caracteres!")
        String name,
        @NotBlank(message = "Campo obrigatorio!")
        @Email(message = "Email invalido!")
        String email,
        @NotNull(message = "Campo obrigatorio!")
        Set<Role> role
) {
}
