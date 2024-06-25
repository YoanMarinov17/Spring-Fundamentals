package com.dictionaryapp.controller;


import com.dictionaryapp.config.LoggedUser;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordsService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {



    private final LoggedUser loggedUser;

    private final WordRepository wordRepository;
    private WordsService wordService;

    public HomeController(LoggedUser loggedUser, WordRepository wordRepository, WordsService wordService) {
        this.loggedUser = loggedUser;
        this.wordRepository = wordRepository;
        this.wordService = wordService;
    }


    @GetMapping("/")
    public String notLogged(){
        if (loggedUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }


    @GetMapping("/home")
    public String logged(Model model){
        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        List<Word> spanishWords = wordService.findSpanishWords();
        List<Word> germanWords = wordService.findGermanWords();
        List<Word> italianWords = wordService.findItalianWords();
        List<Word> frenchWords = wordService.findFrenchWords();

        model.addAttribute("spanishWords", spanishWords);
        model.addAttribute("germanWords", germanWords);
        model.addAttribute("italianWords", italianWords);
        model.addAttribute("frenchWords", frenchWords);

        long count = wordRepository.count();

        model.addAttribute("wordsCount", count);

        return "home";
    }

    @GetMapping("/home/remove-all")
    public String removeAll(){
        if (!loggedUser.isUserLoggedIn()) {
            return "redirect:/";
        }


        return "home";
    }
}
