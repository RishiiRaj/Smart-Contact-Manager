package com.learning.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.scm.entities.User;
import com.learning.scm.forms.UserForm;
import com.learning.scm.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class pageController {

    @Autowired
    private UserService userService;

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
    public String getMethodName(Model model) {
        UserForm userform = new UserForm();
        userform.setName("Rishi");
        model.addAttribute("userForm", userform); // this will be passed in html form
        return new String("register");
    }

    // processing register of new signup
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm)
    // by using @ModelAttibute, the field names in form will get mapped
    // automatically to the same name variables in UserForm class
    {
        System.out.println("Processing register");
        // fetch form data
        // validate form data
        System.out.println(userForm);
        // save data to DB

        // USer form se user banaya hai
        User user = User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic(
                        "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQApAMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABgcCBAUDAf/EADcQAAICAQIDBQUGBQUAAAAAAAABAgMEBREGITESQVFhcRMygZGxInKhwdHhFCQzUnMVI0JTYv/EABYBAQEBAAAAAAAAAAAAAAAAAAABAv/EABYRAQEBAAAAAAAAAAAAAAAAAAABEf/aAAwDAQACEQMRAD8AtIAGmQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB8bSTbaSXVvkkcjM4m0zGk4q13SXX2Ud18+gHYBHa+MNPlLadd8E+9xT/ADOzhahiZ8XLEvhZt1in9peq6gbIAAAAAAAAAAAAAAAAAAAAAYXXQx6Z3XT7FcFvKXgjMh3G+oSlbXp9TfZilO373cvlz+IHM1zXsjVJuEd68Ze7Xv73mzkD06AqBnTbZRbG2mbhZHpKL2aMABPuGte/1ODx8naOVBb8uli73t4ndKpxr7Ma+u+l7WVy7UfUs7AyoZuHTk18o2QUtvB96+DIrYAAAAAAAAAAAAAAAAAABdSr9XueRqmVbJtt2y238OhaCKqzY9nMyIvutl9WB4gA0gAABOeCLpWaXbU237O17ejW5BiacCR/ksqXjYl+BlUnAAAAAAAAAAAAAAAAAAArjiXHeNreTHblN+0j6S5/Xcscj/Fukzz8WORjre+hP7KXOUfD1/cCCAJcgVAAAOpYPCOP7DRKpS63Nz+D6fgiHaLpdmqZsaYpqqOztn/bH9WWTCKhCMIJKEUlFLuS6EVkAAAAAAAAAAAAAAAAAAAAA4Wr8M4mfN3Uy/h75e84x3jLzaI9fwnqtcn7ONN0fGFmz+T2J5Kca12pyUUu9vZGlZrOmUvazPx0/BT3+gVCo8M6vJ7fwqXrZHb6nSweDr21LPyIQjvzhTvJ/PoSCOv6RJ7LPpXrv+ht0ZuLk/0Mmmz7lib+QR8wsPHwaFRi1quteHVvxb7zYAAAAAAAAAAAAAAAAAAAHN1vV6tJx1Oa7d0/6de+2/m/IDZzs3HwaHblWxrj59X5Jd5E9R4uyLW4afBUQ6e0mt5P4dEcHOzcjPyHflT7c30XdHyS7jXA9cjJyMmXayLp2v8A9y3PL0ALiA6Pdcn4gDB08DXtRwmuxkOcO+u37Sf5olWk8TYudKNV/wDL3vklJ/Zl6P8AUgQCxbYITw7xJPGcMXPk50PlGxvnX6+K+hNU00mnun0fiQfQAAAAAAAAAAAAGtqObVp+HZk3e7Be7/c+5FaZ+Zbn5dmTe95zfRdEu5LyO5xpqDvzY4dcv9ujnLZ9Z/stvxI4AABUoACgAAAAAEs4O1jmtNyJf4ZPr939CJmUZyrkpwk1KD7Sa6polWLZBp6TmrUNPpyVspSW00u6S6m4QAAAAAAAADzvujj0WXz92qLm/NLmehyeKLXVoWVtyc1GHwbAryyyd1krbXvObcpPzZiAWJQAFAAAAAAAAAAEqxLOBMpqWViSfJpWw+j/ACJeV5wla6tdoS/5px+a/YsJdCD6AAAAAAAAcLjN7aJLzsh9QAICACxkABVAAAAAAAAAAB0eHXtrmFt/2r6Msrw9ACLAAEAAAf/Z")
                .build(); // this will give us the user
        userService.saveUser(user);

        System.out.println("user saved");
        // message = "registeration successful"
        // redirect to login page
        return "redirect:/register";
    }

}
