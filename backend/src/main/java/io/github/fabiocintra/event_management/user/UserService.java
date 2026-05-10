package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.exceptions.MethodErrorException;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public User findById(String id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        return userOptional.get();
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
