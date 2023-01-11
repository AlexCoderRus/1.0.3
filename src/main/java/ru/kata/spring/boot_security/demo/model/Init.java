
package ru.kata.spring.boot_security.demo.model;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImp;

@Component
public class Init {

    private final UserDetailServiceImp userService;

    public Init(UserDetailServiceImp userService) {
        this.userService = userService;
    }
    public  void createUser() {

        User user = new User("user@mail.com", "100", "user", 20, "user");
        user.addRole(new Role("ROLE_USER"));
        User admin = new User("admin@mail.com", "100", "admin", 24, "admin");
        admin.addRole(new Role("ROLE_ADMIN"));

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
