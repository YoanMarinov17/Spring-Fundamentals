package com.example.shopping.controller;

import com.example.shopping.model.dtos.UserRegistrationDTO;
import com.example.shopping.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute(name = "userRegistrationDTO")
    public UserRegistrationDTO userRegistrationForm(){
        return new UserRegistrationDTO();
    }


    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.setViewName("register");
        return model;
    }


    @PostMapping("/register")
    public ModelAndView register(ModelAndView model,
                                 @Valid UserRegistrationDTO userRegistrationDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.setViewName("register");
            return model;
        }

        this.userService.saveUser(userRegistrationDTO);

        model.setViewName("redirect:/home");
        return model;
    }

}
