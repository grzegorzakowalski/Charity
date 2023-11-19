package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;
    @GetMapping("/register")
    public String registrationFormView(Model model, @RequestParam(required = false, name = "exist") String exist){
        model.addAttribute("user", new User());
        model.addAttribute("exist", exist);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user){
        if( userRepository.findUserByUsername(user.getUsername()) == null){
            userRepository.save(user);
            return "redirect:/";
        }
        return "redirect:/register?exist=true";
    }
}
