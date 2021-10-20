package ru.slava.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.slava.model.User;
import ru.slava.repository.UserRepository;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
    * Trouble was in coding database mysql - latin 1 / iso-8859-1.
    * Can be change on utf-8 in url string that doing connect to database - application.yml
    * @return All issues in table
    */
    @RequestMapping(method = RequestMethod.GET, value = "/get_issues")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
    * user.setName(Charset.defaultCharset().displayName());
    * Get charset page / jvm
    *
    * @param user - Body request, full data about user(all fields)
    */
    @PostMapping("/add_issue")
    public void addUser(@RequestBody User user) {
        try {
            Integer id = user.getId();
            if (id > 0 && !userRepository.findById(id).isPresent()) {
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get index row in table for update her
     * @param id Index change row
     * @return Good - Object User, else null point
     */
    @GetMapping("/get_issue_update")
    @ResponseBody
    public User getUserUpdate(@RequestParam Integer id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    /**
    * Basic RequestParam,
    * and ajax response without contentType, dataType: 'text'
    * @param id Index issue
    * @param name Field header's table in database table
    * @param value Fill field this is value
    */
    @PostMapping("/update_issue")
    public void addUser(@RequestParam("id") String id,
                        @RequestParam("name") String name,
                        @RequestParam("value") String value) {
        if (userRepository.findById(Integer.parseInt(id)).isPresent()) {
            userRepository.updateById(Integer.parseInt(id), name, value);
        }
    }

    /**
     * Get index row in table for update her
     * @param id Index delete row
     */
    @GetMapping("/get_issue_delete")
    @ResponseBody
    public void getUserDelete(@RequestParam Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }


    /**
    * Decode in need charset code
    * @param text Text for decode
    * @param code String for decode / name charset
    * @return String with decode charset
    */
    private String decode(String text, String code) throws IOException {
        return
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(text.getBytes()),
                                Charset.forName(code)))
                        .readLine();
    }
}
