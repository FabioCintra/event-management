package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.user.model.dto.CreateUserRequest;
import io.github.fabiocintra.event_management.user.model.dto.UpdateUserRequest;
import io.github.fabiocintra.event_management.user.model.dto.UserResponse;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;

@Mapper
public class UserMapper {

    public User createToEntity(CreateUserRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.passwordHash());
        user.setRole(request.role());
        return user;
    }

    public User updateToEntity(UpdateUserRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setRole(request.role());
        return user;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getRole(),
                user.getOrders(),
                user.getEvents()
        );
    }

}
