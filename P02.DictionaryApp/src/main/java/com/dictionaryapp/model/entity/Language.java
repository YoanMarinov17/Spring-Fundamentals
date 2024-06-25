package com.dictionaryapp.model.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private LanguageEnum name;


    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "language")
    private Set<Word> words;


    public Language() {
        this.words = new HashSet<>();
    }

    public Language(LanguageEnum name, String description) {
        super();
        this.name = name;
        this.description = description;

    }

    public LanguageEnum getName() {
        return name;
    }

    public void setName(LanguageEnum languageEnum) {
        this.name = languageEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
