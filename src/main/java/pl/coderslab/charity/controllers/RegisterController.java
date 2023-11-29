package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailServiceImpl;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;

    @GetMapping("/register")
    public String registrationFormView(Model model, @RequestParam(required = false, name = "exist") String exist,
                                       @RequestParam(required = false, name = "UUID")String UUID){
        if( UUID != null){
            User user = userRepository.findUserByUUID(UUID);
            if( user != null){
                user.setActive(true);
                user.setEmailVerified(true);
                user.setUUID("");
                userRepository.save(user);
                return "redirect:/login";
            }
        }
        model.addAttribute("user", new User());
        model.addAttribute("exist", exist);
        model.addAttribute("active","register");
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, HttpServletRequest request){
        if( userRepository.findUserByUsername(user.getUsername()) == null){
            user.setRole("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDonations(new ArrayList<>());
            user.setActive(false);
            user.setUUID(UUID.randomUUID().toString());
            user.setEmailVerified(false);
            emailService.sendSimpleMessage(user.getUsername(), "Registration","To register click this link: " + request.getRequestURL() +"?UUID=" + user.getUUID());
            userRepository.save(user);
            return "redirect:/";
        }
        return "redirect:/register?exist=true";
    }
}
