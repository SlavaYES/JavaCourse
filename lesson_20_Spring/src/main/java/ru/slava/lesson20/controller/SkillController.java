package ru.slava.lesson20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slava.lesson20.entity.Skill;
import ru.slava.lesson20.service.SkillService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Skill skill) {
        return ResponseEntity.ok(skillService.save(skill));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Skill> skill = skillService.findById(id);
        return Objects.isNull(skillService.findById(id))
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(skill);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @GetMapping("/by/{title}")
    public ResponseEntity findByTitle(@PathVariable String title) {
        Skill skill = skillService.findByTitle(title);
        return Objects.isNull(skill)
                        ? ResponseEntity.notFound().build()
                        : ResponseEntity.ok(skill);
    }
}
