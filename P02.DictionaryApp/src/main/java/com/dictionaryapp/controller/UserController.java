package com.dictionaryapp.controller;



import com.dictionaryapp.model.dto.UserLoginDto;
import com.dictionaryapp.model.dto.UserRegisterDto;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("registerData")
    public UserRegisterDto createEmptyUserDto() {
        return new UserRegisterDto();
    }


    @ModelAttribute("loginData")
    public UserLoginDto loginUserDto() {
        return new UserLoginDto();
    }

    @GetMapping("/register")
    public String viewRegister() {

        return "register";
    }


    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto data, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userService.register(data)) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }


    @GetMapping("/login")
    public  String viewLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDto data, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
            return "redirect:/login";
        }

        boolean success = userService.login(data);

        if (!success){
            redirectAttributes.addFlashAttribute("loginData", data);
            redirectAttributes.addFlashAttribute("userPassMismatch", true);
            return "redirect:/login";
        }

        return "redirect:/home";

    }


    @PostMapping("/logout")
    public String logout(){

        userService.logout();

        return "redirect:/";
    }
}
