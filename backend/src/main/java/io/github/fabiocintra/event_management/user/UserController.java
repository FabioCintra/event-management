package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.user.model.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequest request){
        User user = userMapper.toEntity(request);
        userService.createUser(user);
    }

}
