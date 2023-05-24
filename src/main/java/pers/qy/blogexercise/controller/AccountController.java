package pers.qy.blogexercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin_login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "admin_register";
    }

}
