package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/register")
    public String registrationFormView(Model model, @RequestParam(required = false, name = "exist") String exist){
        model.addAttribute("user", new User());
        model.addAttribute("exist", exist);
        model.addAttribute("active","register");
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user){
        if( userRepository.findUserByUsername(user.getUsername()) == null){
            user.setRole("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDonations(new ArrayList<>());
            userRepository.save(user);
            return "redirect:/";
        }
        return "redirect:/register?exist=true";
    }
}
