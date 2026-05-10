package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.utils.exceptions.CreateException;
import io.github.fabiocintra.event_management.utils.exceptions.EmailRegisteredException;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.annotations.Validate;
import lombok.RequiredArgsConstructor;

@Validate
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository userRepository;

    public void userIsValid(User user) {
        String email = user.getEmail();

        if(userRepository.existsByEmail(email) && !isSameUser(user)) {
            throw new EmailRegisteredException("Email already exists!");
        }
    }

    private Boolean isSameUser(User user) {
        User userFindedByEmail = userRepository.findByEmail(user.getEmail());
        return user.getId().equals(userFindedByEmail.getId());
    }


}
