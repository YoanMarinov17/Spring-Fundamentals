package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findLanguageByName(LanguageEnum name);
}
