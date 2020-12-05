package ru.slava.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.slava.model.User;
import ru.slava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping(value = "/get_users")
    @RequestMapping(method = RequestMethod.GET, value = "/get_users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @PostMapping("/add_user")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
