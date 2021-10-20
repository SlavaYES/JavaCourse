package ru.slava.lesson20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slava.lesson20.entity.Programmer;
import ru.slava.lesson20.repository.ProgrammerRepository;

import java.util.*;
import java.util.Optional;

@Service
public class ProgrammerService {

    private ProgrammerRepository programmerRepository;

    @Autowired
    public ProgrammerService(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public Programmer save(Programmer programmer) {
        return programmerRepository.save(programmer);
    }

    public Optional<Programmer> findById(Long id) {
        return programmerRepository.findById(id);
    }

    public List<Programmer> findAll() {
        return programmerRepository.findAll();
    }
}
