package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.LanguageEnum;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public class AddWordDto {


    @Size(min = 2, max = 40, message = "Must be between 2 and 40")
    private String term;


    @Size(min = 2, max = 80, message = "Must be between 2 and 80")
    private String translation;


    @Size(min = 2, max = 200, message = "Must be between 2 and 200")
    private String example;


    @PastOrPresent(message = "Must be past or present date")
    private LocalDate inputDate;


    private LanguageEnum language;


    public AddWordDto() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
