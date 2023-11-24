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
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
    public String adminCrudView(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                                @RequestParam(name = "msg", required = false) String msg){
        model.addAttribute("users", userService.getAllWithout(currentUser.getUser()));
        model.addAttribute("donations", donationRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("msg", msg != null ? msg.replaceAll("_", " ") : null);
        return "panel-crud";
    }

    @GetMapping("/user/add")
    public String addUserView(Model model, @RequestParam(name = "exist", required = false) String exist){
        model.addAttribute("user", new User());
        model.addAttribute("roles", Stream.of("ROLE_ADMIN","ROLE_USER").toList());
        model.addAttribute("exist", exist);
        return "panel-user-add";
    }

    @PostMapping("/user/add") // TODO fix relogging bug
    public String addUser(@RequestParam User user){
        if( userRepository.findUserByUsername(user.getUsername()) != null){
            return "redirect:/panel/user/add?exist=true";
        }
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/panel/crud?msg=user_added";
    }

    @GetMapping("/institution/add")
    public String addInstitutionView(Model model){
        model.addAttribute("institution", new Institution());
        return "panel-institution-add";
    }

    @PostMapping("/institution/add")
    public String addInstitution(Institution institution){
        institutionRepository.save(institution);
        return "redirect:/panel/crud?msg=institution_added";
    }
}
