package foodly_backend.controller;

import foodly_backend.entity.UserEntity;
import foodly_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getUser() {
        return this.userService.getUsers();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody UserEntity user) {
        this.userService.createUser(user);
    }
}

