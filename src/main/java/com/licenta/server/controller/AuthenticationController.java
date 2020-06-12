package com.licenta.server.controller;

import com.licenta.server.model.User;
import com.licenta.server.repository.UserRepository;
import com.licenta.server.service.UserService;
import com.licenta.server.controller.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping(path = "/")
public class AuthenticationController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    UserService userService;

    @GetMapping(path = "getAll")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmedPassword") String confirmedPassword, @RequestParam("email") String email) {

        User user = new User(username, password, email);
        if (password.matches(confirmedPassword) == false) {
            return "Passwords do not match";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        if (userRepo.existsUserByUsername(username) == true) {
            return "Username already taken";
        } else if (userRepo.existsUserByEmail(email) == true) {
            return "Email address already taken";
        } else if (username == "") {
            return "Please provide an username";
        } else if (email == "") {
            return "Please provide an email";
        } else if (password == "") {
            return "Please provide a password";
        } else {
            userRepo.save(user);
            return "SUCCESSFUL";
        }
    }

    @PostMapping("login")
    public @ResponseBody
    User user(@RequestParam("username") String username, @RequestParam("password") String password) {

        if (this.userRepo.findUserByUsername(username) != null) {
            User user = this.userRepo.findUserByUsername(username);
            if (passwordMatchesHash(password, user)) {
                return user;
            }
        }

        return null;
    }

    public boolean passwordMatchesHash(String password, User user) {
        return encoder.matches(password, user.getPassword());
    }

}
