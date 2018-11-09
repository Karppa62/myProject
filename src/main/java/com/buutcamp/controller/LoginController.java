package com.buutcamp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    private String showLoginPage(Model model) {

        model.addAttribute("title", "Team manager - login");
        return "login-page";
    }

    @RequestMapping("/loginSuccessful")
    private String loginSuccessful(Model model) {

        return "redirect:/players/";
    }

    @GetMapping("/logout.done")
        private String logout() {

        return "redirect:/showLoginPage";
    }
}
