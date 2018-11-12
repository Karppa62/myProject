package com.buutcamp.controller;

import com.buutcamp.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired // defined in SecurityConfig
    private UserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {

        model.addAttribute("myuser", new MyUser());
        model.addAttribute("title", "Registration form");

        return "registration-form";
    }

    @PostMapping("/processRegister")
    public String processRegistration(
            @Valid @ModelAttribute("myuser") MyUser user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registrationError", "Username or password cannot be empty");
            return "redirect:/register";
        }

        user.setRole("manager");
        String username = user.getUsername();

        if (userDetailsManager.userExists(username)) {
            redirectAttributes.addFlashAttribute("registrationError", "Username already exists");
            return "redirect:/register";
        }
        // validation passed -> add user to database
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList();
        authorities.add(new SimpleGrantedAuthority("ROLE_manager"));

        // add other roles if necessary
        if (!user.getRole().equals("manager")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        }

        // create a new user and save to database from: import org.springframework.security.core.userdetails.User;
        User tempUser = new User(username, encodedPassword, authorities);

        userDetailsManager.createUser(tempUser);
        redirectAttributes.addFlashAttribute("registrationComplete", "Registration successful");
        return "redirect:/showLoginPage";
    }
}
