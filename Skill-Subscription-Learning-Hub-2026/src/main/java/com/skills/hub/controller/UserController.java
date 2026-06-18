package com.skills.hub.controller;

import com.skills.hub.model.User;
import com.skills.hub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

/*
=========================================================
WHAT IS THIS FILE?
Handles user actions like register and login
=========================================================
*/
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        // STEP 1: Return register page
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        // STEP 1: call service.registerUser(user)
        User saved = userService.registerUser(user);
        // STEP 2: if success → redirect to login
        if (saved != null) {
            return "redirect:/login";
        }
        // STEP 3: else → stay on register page
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        // STEP 1: return login page
        return "login";
    }

   @PostMapping("/login")
public String login(@RequestParam String email,
                    @RequestParam String password,
                    HttpSession session,
                    Model model) {

    User user = userService.login(email, password);

    if (user == null) {
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    // Save logged-in user to session
    session.setAttribute("loggedUser", user);
    return "redirect:/packs";
}
	@PostMapping("/logout")
public String logout(HttpSession session) {
    // Step 1: destroy the session
    session.invalidate();
    // Step 2: redirect to login page
    return "redirect:/login";
}

    public UserService getUserService() {
        return userService;
    }
}
