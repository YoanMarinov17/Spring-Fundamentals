package com.planner.controller;

import com.planner.model.dtos.UserLoginDTO;
import com.planner.service.UserService;
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
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }
    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("login");
        return model;
    }

    @PostMapping("/login")
    public ModelAndView login(ModelAndView model,
                                 @Valid UserLoginDTO userLoginDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.setViewName("login");
            if (userLoginDTO.getUsername().length() >= 3 && userLoginDTO.getUsername().length() <= 20){
                model.addObject("incorrectMessage",true);
            }else {
                model.addObject("errorMessage",true);
            }
            return model;
        }
        this.userService.loginUser(userLoginDTO);
        model.setViewName("redirect:/home");
        return model;
    }
}
