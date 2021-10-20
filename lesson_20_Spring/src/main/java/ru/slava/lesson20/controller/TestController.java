package ru.slava.lesson20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slava.lesson20.entity.Test;
import ru.slava.lesson20.service.TestService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Test test) {
        return ResponseEntity.ok(testService.save(test));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Test> test = testService.findById(id);
        return Objects.isNull(testService.findById(id))
                        ? ResponseEntity.notFound().build()
                        : ResponseEntity.ok(test);
    }

    @GetMapping
    public ResponseEntity finAll() {
        return ResponseEntity.ok(testService.findAll());
    }
}
