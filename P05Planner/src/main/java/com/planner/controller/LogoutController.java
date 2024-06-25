package com.planner.controller;

import com.planner.model.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class LogoutController {
    private final CurrentUser currentUser;

    public LogoutController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/logout")
    public ModelAndView register(ModelAndView model) {
        this.currentUser.clear();
        model.setViewName("redirect:/");
        return model;
    }
}
