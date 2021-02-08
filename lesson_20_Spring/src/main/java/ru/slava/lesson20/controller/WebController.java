package ru.slava.lesson20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.slava.lesson20.entity.Language;
import ru.slava.lesson20.entity.Programmer;

import java.util.Arrays;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user",
                new Programmer(1L, "Slava",
                        Arrays.asList(new Language(1L, "Test", "Test desc", null),
                                new Language(2L, "Test1", "Test desc", null)),
                        null ));

        return "index";
    }

    @GetMapping("/create_developer")
    public String developer() {
        return "developer";
    }

    @GetMapping("/create_language")
    public String language() {
        return "language";
    }
}
