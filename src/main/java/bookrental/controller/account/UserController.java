package bookrental.controller.account;

import bookrental.model.account.User;
import bookrental.service.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @DeleteMapping("/user")
    public User deleteAccount(@RequestParam int id) {
        return userService.deleteAccount(id);
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
