package com.bonappetit.controller;


import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.dto.UserLoginDto;
import com.bonappetit.model.entity.dto.UserRegisterDto;
import com.bonappetit.repo.UserRepo;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {


    private UserRepo userRepo;

    private UserService userService;

    private final LoggedUser loggedUser;

    public UserController(UserRepo userRepo, UserService userService, LoggedUser loggedUser) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("registerData")
    public UserRegisterDto createEmptyUserDto() {
        return new UserRegisterDto();
    }


    @ModelAttribute("loginData")
    public UserLoginDto loginUserDto() {
        return new UserLoginDto();
    }


// Гет заявка за да видим view на регистер
    @GetMapping("/register")
    public String viewRegister(){
        if (loggedUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        return "register";
    }



    // Правим Пост заявка за да извлечем данните от ДТО и да ги добавим към базата
    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto data, BindingResult bindingResult, RedirectAttributes redirectAttributes){


        if (loggedUser.isUserLoggedIn()){
            return "redirect:/home";
        }

        //Ако в bindingResult имаме грешка, трябва да я извлечем
        if (bindingResult.hasErrors() || !userService.register(data)){
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }



    @GetMapping("/login")
    public  String viewLogin(){
        if (loggedUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        return "login";
    }


    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto data, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (loggedUser.isUserLoggedIn()){
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData",bindingResult);
            return "redirect:/login";
        }

        boolean success = userService.login(data);

        if (!success){
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("userPassMissmatch", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(){
        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        loggedUser.logout();
        return "redirect:/";
    }


}
