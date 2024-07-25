package com.learning.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.scm.entities.User;
import com.learning.scm.forms.UserForm;
import com.learning.scm.helpers.Message;
import com.learning.scm.helpers.MessageType;
import com.learning.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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

    // sirf slash url ko home pe redirect kar dega
    @GetMapping("/")
    public String getMethodName() {
        return "redirect:/home";
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
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session)
    // by using @ModelAttibute, the field names in form will get mapped
    // automatically to the same name variables in UserForm class
    {
        System.out.println("Processing register");
        // fetch form data
        System.out.println(userForm);
        // validate form data
        if (rBindingResult.hasErrors()) {
            return "register";
        }
        // save data to DB

        // USer form se user banaya hai
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic(
        // "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQApAMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABgcCBAUDAf/EADcQAAICAQIDBQUGBQUAAAAAAAABAgMEBREGITESQVFhcRMygZGxInKhwdHhFCQzUnMVI0JTYv/EABYBAQEBAAAAAAAAAAAAAAAAAAABAv/EABYRAQEBAAAAAAAAAAAAAAAAAAABEf/aAAwDAQACEQMRAD8AtIAGmQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB8bSTbaSXVvkkcjM4m0zGk4q13SXX2Ud18+gHYBHa+MNPlLadd8E+9xT/ADOzhahiZ8XLEvhZt1in9peq6gbIAAAAAAAAAAAAAAAAAAAAAYXXQx6Z3XT7FcFvKXgjMh3G+oSlbXp9TfZilO373cvlz+IHM1zXsjVJuEd68Ze7Xv73mzkD06AqBnTbZRbG2mbhZHpKL2aMABPuGte/1ODx8naOVBb8uli73t4ndKpxr7Ma+u+l7WVy7UfUs7AyoZuHTk18o2QUtvB96+DIrYAAAAAAAAAAAAAAAAAABdSr9XueRqmVbJtt2y238OhaCKqzY9nMyIvutl9WB4gA0gAABOeCLpWaXbU237O17ejW5BiacCR/ksqXjYl+BlUnAAAAAAAAAAAAAAAAAAArjiXHeNreTHblN+0j6S5/Xcscj/Fukzz8WORjre+hP7KXOUfD1/cCCAJcgVAAAOpYPCOP7DRKpS63Nz+D6fgiHaLpdmqZsaYpqqOztn/bH9WWTCKhCMIJKEUlFLuS6EVkAAAAAAAAAAAAAAAAAAAAA4Wr8M4mfN3Uy/h75e84x3jLzaI9fwnqtcn7ONN0fGFmz+T2J5Kca12pyUUu9vZGlZrOmUvazPx0/BT3+gVCo8M6vJ7fwqXrZHb6nSweDr21LPyIQjvzhTvJ/PoSCOv6RJ7LPpXrv+ht0ZuLk/0Mmmz7lib+QR8wsPHwaFRi1quteHVvxb7zYAAAAAAAAAAAAAAAAAAAHN1vV6tJx1Oa7d0/6de+2/m/IDZzs3HwaHblWxrj59X5Jd5E9R4uyLW4afBUQ6e0mt5P4dEcHOzcjPyHflT7c30XdHyS7jXA9cjJyMmXayLp2v8A9y3PL0ALiA6Pdcn4gDB08DXtRwmuxkOcO+u37Sf5olWk8TYudKNV/wDL3vklJ/Zl6P8AUgQCxbYITw7xJPGcMXPk50PlGxvnX6+K+hNU00mnun0fiQfQAAAAAAAAAAAAGtqObVp+HZk3e7Be7/c+5FaZ+Zbn5dmTe95zfRdEu5LyO5xpqDvzY4dcv9ujnLZ9Z/stvxI4AABUoACgAAAAAEs4O1jmtNyJf4ZPr939CJmUZyrkpwk1KD7Sa6polWLZBp6TmrUNPpyVspSW00u6S6m4QAAAAAAAADzvujj0WXz92qLm/NLmehyeKLXVoWVtyc1GHwbAryyyd1krbXvObcpPzZiAWJQAFAAAAAAAAAAEqxLOBMpqWViSfJpWw+j/ACJeV5wla6tdoS/5px+a/YsJdCD6AAAAAAAAcLjN7aJLzsh9QAICACxkABVAAAAAAAAAAB0eHXtrmFt/2r6Msrw9ACLAAEAAAf/Z")
        // .build(); // this will give us the user
        // userService.saveUser(user);

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic(
                "data:image/png;base64,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");
        User savedUser = userService.saveUser(user); // save in DB
        System.out.println("user saved");
        // message = "registeration successful"
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);
        // redirect to login page
        return "redirect:/register";
    }

}
