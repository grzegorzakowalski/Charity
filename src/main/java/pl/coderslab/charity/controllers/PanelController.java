package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/panel")
public class PanelController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/user")
    public String userUserPanelView(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user",currentUser.getUser());
        model.addAttribute("bags",userService.getUserBagsDonatedAmount(currentUser.getUser()));
        model.addAttribute("donations",userService.getUserDonationsAmount(currentUser.getUser()));
        return "panel-user";
    }

    @PostMapping("/user")
    public String changeUserData(User user){

        return "redirect:/panel/user";
    }

    @PostMapping("/password")
    public String changeUsersPassword(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam(name = "password") String password){

        return "redirect:/panel/user";
    }
}
