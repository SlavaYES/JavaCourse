package ru.slava;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/get_user")
    public User getTestUser() {
        return new User(1, "900", "Ivan");
    }

}
