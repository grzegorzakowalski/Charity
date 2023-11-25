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
    private final List<String> roles = Stream.of("ROLE_ADMIN","ROLE_USER").toList();

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
                                @RequestParam(name = "msg", required = false) String msg,
                                @RequestParam(name = "error", required = false) String error){
        model.addAttribute("users", userService.getAllWithout(currentUser.getUser()));
        model.addAttribute("donations", donationRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("msg", msg != null ? msg.replaceAll("_", " ") : null);
        model.addAttribute("error", error != null ? error.replaceAll("_", " ") : null);
        return "panel-crud";
    }

    @GetMapping("/user/add")
    public String addUserView(Model model, @RequestParam(name = "exist", required = false) String exist){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        model.addAttribute("exist", exist);
        return "panel-user-add";
    }

    @PostMapping("/user/add")
    public String addUser(User user){
        if( userRepository.findUserByUsername(user.getUsername()) != null){
            return "redirect:/panel/user/add?exist=true";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/panel/crud?msg=user_added";
    }

    @GetMapping("/user/modify")
    public String modifyUserView(Model model, @RequestParam(name = "id") long id){
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("roles", roles);
        return "panel-user-modify";
    }

    @PostMapping("/user/modify")
    public String modifyUser(User user){
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if( oldUser == null){
            return "redirect:/panel/crud?error=user_not_found";
        }
        if(user.getPassword().isEmpty()){
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        return "redirect:/panel/crud?msg=user_modify";
    }

    @GetMapping("/user/delete")
    public String deleteUserConfirmationView(Model model, @RequestParam(name = "id") long id){
        User user = userRepository.findById(id).orElse(null);
        if( user == null){
            return "redirect:/panel/crud?error=user_not_found";
        }
        model.addAttribute("user", user);
        return "panel-user-delete";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam(name = "id") long id){
        User user = userRepository.findById(id).orElse(null);
        if( user == null){
            return "redirect:/panel/crud?error=user_not_found";
        }
        userRepository.delete(user);
        return "redirect:/panel/crud?msg=user_deleted";
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

    @GetMapping("/institution/modify")
    public String modifyInstitutionView(Model model, @RequestParam(name = "id") long id){
        Institution institution = institutionRepository.findById(id).orElse(null);
        if( institution == null){
            return "redirect:/panel/crud?error=institution_not_found";
        }
        model.addAttribute("institution", institution);
        return "panel-institution-modify";
    }

    @PostMapping("/institution/modify")
    public String modifyInstitution(Institution institution){
        if( !institutionRepository.existsById(institution.getId())){
            return "redirect:/panel/crud?error=institution_not_found";
        }
        institutionRepository.save(institution);
        return "redirect:/panel/crud?msg=institution_modified";
    }

    @GetMapping("/institution/delete")
    public String deleteInstitutionConfirmationView(@RequestParam(name = "id") long id, Model model){
        Institution institution = institutionRepository.findById(id).orElse(null);
        if( institution == null){
            return "redirect:/panel/crud?error=institution_not_found";
        }
        model.addAttribute("institution", institution);
        return "panel-institution-delete";
    }

    @PostMapping("/institution/delete")
    public String deleteInstitution(@RequestParam( name = "id") long id){
        Institution institution = institutionRepository.findById(id).orElse(null);
        if( institution == null){
            return "redirect:/panel/crud?error=institution_not_found";
        }
        institutionRepository.deleteById(id);
        return "redirect:/panel/crud?msg=institution_deleted";
    }
}
