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
import pl.coderslab.charity.CategoryRepository;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
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
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/user")
    public String userPanelView(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user",currentUser.getUser());
        model.addAttribute("bags",userService.getUserBagsDonatedAmount(currentUser.getUser()));
        model.addAttribute("donations",userService.getUserDonationsAmount(currentUser.getUser()));
        return "panel-user";
    }

    @PostMapping("/user")
    public String changeUserData(User user){
        user.setPassword(userRepository.findUserByUsername(user.getUsername()).getPassword());
        userRepository.save(user);
        return "redirect:/panel/user";
    }

    @PostMapping("/password")
    public String changeUsersPassword(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam(name = "password") String password){
        User user = currentUser.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/panel/user";
    }

    @GetMapping("/crud")
    public String adminCrudView(Model model){

        return "panel-crud";
    }
}
