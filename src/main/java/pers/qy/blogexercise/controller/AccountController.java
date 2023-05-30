package pers.qy.blogexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.qy.blogexercise.model.entity.Account;
import pers.qy.blogexercise.service.AccountService;

@Controller
@RequestMapping("/admin")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin_login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "admin_register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String userName, @RequestParam String userEmail, @RequestParam
                           String password) {
        if (accountService.createAccount(userName, userEmail, password)) {
            return "redirect:/admin/login";
        } else {
            return "redirect:/admin/register";
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String userEmail, @RequestParam String password) {
//        Account loginAccount = accountService.login(userEmail, password);
//        System.out.println(loginAccount);
//        return "success";
//    }

}
