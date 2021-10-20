package ru.slava.lesson20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slava.lesson20.entity.Language;
import ru.slava.lesson20.repository.LanguageRepository;

import java.util.*;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public Optional<Language> findById(Long id) {
        return languageRepository.findById(id);
    }

    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
