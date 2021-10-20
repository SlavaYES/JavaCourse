package ru.slava.lesson20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slava.lesson20.entity.Language;
import ru.slava.lesson20.service.LanguageService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.save(language));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Language> language = languageService.findById(id);
        return Objects.isNull(languageService.findById(id))
                        ? ResponseEntity.notFound().build()
                        : ResponseEntity.ok(language);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(languageService.findAll());
    }
}
