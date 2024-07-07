package com.learning.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pageController {
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page handler");
        // setting data to view
        model.addAttribute("name", "My Handler hai ye  ");
        model.addAttribute("Youtube", "Mera youtube hai ye  ");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);
        System.out.println("About Page handler");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services Page handler");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String getMethodName() {
        return new String("register");
    }

}
