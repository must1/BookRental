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
    public void createUser(@RequestBody User newUser) {
        userService.createUser(newUser);
    }

    @DeleteMapping("/user")
    public void deleteAccount(@RequestParam int id) {
        userService.deleteAccount(id);
    }

    //do testów
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
