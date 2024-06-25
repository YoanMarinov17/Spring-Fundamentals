package com.dictionaryapp.controller;


import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.AddWordDto;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WordController {


    private WordsService wordsService;
    private final LoggedUser loggedUser;

    private final WordRepository wordRepository;

    public WordController(WordsService wordsService, LoggedUser loggedUser, WordRepository wordRepository) {
        this.wordsService = wordsService;
        this.loggedUser = loggedUser;
        this.wordRepository = wordRepository;
    }


    @GetMapping("/word")
    public String viewAddWord() {
        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        return "word-add";

    }


    @PostMapping("/word")
    public String doAddWord(@Valid AddWordDto data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addWordData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWordData", bindingResult);
            return "redirect:/word";
        }

        wordsService.add(data);


        return "redirect:/home";
    }


    @GetMapping("/words/{id}")
    public String deleteWord(@PathVariable String id) {
        if (!loggedUser.isUserLoggedIn()) {
            return "redirect:/";
        }

        wordsService.delete(id);

return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAllWords(Model model) {


        wordRepository.deleteAll();

        return "redirect:/home";
    }



}
