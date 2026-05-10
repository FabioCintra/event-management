package io.github.fabiocintra.event_management.user;

import io.github.fabiocintra.event_management.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByEmail(String email);
    User findByEmail(String email);

}
