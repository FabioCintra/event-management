package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.exceptions.EmailRegisteredException;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.Validate;
import lombok.RequiredArgsConstructor;

@Validate
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    public void userIsValid(User user) {
        String email = user.getEmail();

        if(userRepository.existsByEmail(email)) {
            throw new EmailRegisteredException("Email already exists!");
        }
    }


}
