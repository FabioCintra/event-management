package io.github.fabiocintra.event_management;

import io.github.fabiocintra.event_management.user.UserService;
import io.github.fabiocintra.event_management.user.model.Role;
import io.github.fabiocintra.event_management.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class EventManagementApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void createUser() {
		User user = new User();
		user.setName("Fabio");
		user.setEmail("fh076417@gmail.com");
		user.setPasswordHash("fabin123");
		user.setRole(Set.of(Role.ATTENDEE, Role.ORGANIZER));
		userService.createUser(user);
	}

}
