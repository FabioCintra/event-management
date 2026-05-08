package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidate userValidate;

    public void createUser(User user) {
        userValidate.userIsValid(user);
        //criptografar a senha
        userRepository.save(user);
    }

}
