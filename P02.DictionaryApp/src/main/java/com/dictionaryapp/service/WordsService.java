package com.dictionaryapp.service;


import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordsService {

 private LanguageRepository languageRepository;

 private LoggedUser loggedUser;

 private final UserRepository userRepository;
    private WordRepository wordRepository;

    private final ModelMapper modelMapper;



    public WordsService(LanguageRepository languageRepository, LoggedUser loggedUser, UserRepository userRepository, WordRepository wordRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
    }



    public boolean add(AddWordDto data){

        if (!loggedUser.isUserLoggedIn()){
            return false;
        }

        Optional<User> byId = userRepository.findById(loggedUser.id());

        if (byId.isEmpty()) {
            return false;
        }

        LanguageEnum name = data.getLanguage();

      Language byName = languageRepository.findLanguageByName(name);


        Word word = new Word();
        word.setTerm(data.getTerm());
        word.setTranslation(data.getTranslation());
        word.setExample(data.getExample());
        word.setDate(data.getInputDate());
       word.setLanguage(byName);
        word.setAddedBy(byId.get());


        wordRepository.save(word);

return true;
    }


    public List<Word> findSpanishWords(){
        Optional<User> addedUser = userRepository.findById(loggedUser.id());

        if (addedUser.isEmpty()){
            return new ArrayList<>();
        }

        Language language = languageRepository.findLanguageByName(LanguageEnum.SPANISH);


      return wordRepository.findByLanguageAndAddedBy(language, addedUser.get());

    }

    public List<Word> findGermanWords() {
        Optional<User> addedUser = userRepository.findById(loggedUser.id());

        if (addedUser.isEmpty()){
            return new ArrayList<>();
        }


        Language germanLanguage = languageRepository.findLanguageByName(LanguageEnum.GERMAN);

        return wordRepository.findByLanguageAndAddedBy(germanLanguage, addedUser.get());
    }

    public List<Word> findItalianWords() {

        Optional<User> addedUser = userRepository.findById(loggedUser.id());

        if (addedUser.isEmpty()){
            return new ArrayList<>();
        }


        Language italianLanguage = languageRepository.findLanguageByName(LanguageEnum.ITALIAN);

        return wordRepository.findByLanguageAndAddedBy(italianLanguage, addedUser.get());

    }

    public List<Word> findFrenchWords() {
        Optional<User> addedUser = userRepository.findById(loggedUser.id());

        if (addedUser.isEmpty()){
            return new ArrayList<>();
        }

        Language frenchLanguage = languageRepository.findLanguageByName(LanguageEnum.FRENCH);

        return wordRepository.findByLanguageAndAddedBy(frenchLanguage, addedUser.get());

    }

    public void delete(String id) {
        Optional<User> addedUser = userRepository.findById(loggedUser.id());

        if (addedUser.isEmpty()){
            return;
        }


        Optional<Word> findWord = wordRepository.findByIdAndAddedBy(id, addedUser.get());

        if (findWord.isEmpty()){
            return;
        }

        wordRepository.delete(findWord.get());
    }

}
