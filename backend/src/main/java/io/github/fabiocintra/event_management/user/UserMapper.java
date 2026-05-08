package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.user.model.dto.UserRequest;
import io.github.fabiocintra.event_management.user.model.dto.UserResponse;
import io.github.fabiocintra.event_management.utils.Mapper;

@Mapper
public class UserMapper {

    public User toEntity(UserRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setRole(request.role());
        return user;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getRole()
        );
    }

}
