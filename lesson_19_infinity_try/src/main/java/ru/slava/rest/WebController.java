package ru.slava.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    /**
     * Return main page
     * @return index.html
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
