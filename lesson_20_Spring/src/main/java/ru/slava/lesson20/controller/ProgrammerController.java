package ru.slava.lesson20.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slava.lesson20.entity.Programmer;
import ru.slava.lesson20.service.ProgrammerService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/programmers")
public class ProgrammerController {

    private ProgrammerService programmerService;

    public ProgrammerController(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Programmer programmer) {
        return ResponseEntity.ok(programmerService.save(programmer));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Programmer> programmer = programmerService.findById(id);
        return Objects.isNull(programmerService.findById(id))
                        ? ResponseEntity.notFound().build()
                        : ResponseEntity.ok(programmer);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(programmerService.findAll());
    }
}

