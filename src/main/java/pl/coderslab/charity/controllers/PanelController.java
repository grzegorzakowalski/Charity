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
import pl.coderslab.charity.entities.*;
import pl.coderslab.charity.repositories.*;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.services.UserService;

import java.util.Comparator;
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
    private final ContactMSGRepository contactMSGRepository;
    private final List<String> ROLES = Stream.of("ROLE_ADMIN","ROLE_USER").toList();
    private final List<Boolean> IS_ACTIVE = Stream.of(true,false).toList();

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

    @GetMapping("/donations")
    public String usersDonationSView(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("sortedDonations", userService.getUsersDonationsSorted(currentUser.getUser()));
        return "panel-donations";
    }

    @GetMapping("/donation/details")
    public String donationDetailsView(Model model, @RequestParam(name = "id")long id){
        Donation donation = donationRepository.findById(id).orElse(null);
        model.addAttribute("donation", donation);
        return "panel-donation-details";
    }

    @PostMapping("/donations/details")
    public String donationDetailsModification(@RequestParam(name = "id") long id,
                                              @RequestParam(name = "isPicked")boolean isPicked){
        Donation donation = donationRepository.findById(id).orElse(null);
        if( donation == null){
            return "redirect:/panel/donations";
        }
        donation.setIsPicked(isPicked);
        donationRepository.save(donation);
        return "redirect:/panel/donations";
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
        model.addAttribute("contactMSGs", contactMSGRepository.findAll().stream()
                .sorted(Comparator.comparing(ContactMSG::getArchived))
                .toList());
        return "panel-crud";
    }

    @GetMapping("/user/add")
    public String addUserView(Model model, @RequestParam(name = "exist", required = false) String exist){
        model.addAttribute("user", new User());
        model.addAttribute("roles", ROLES);
        model.addAttribute("exist", exist);
        return "panel-user-add";
    }

    @PostMapping("/user/add")
    public String addUser(User user){
        if( userRepository.findUserByUsername(user.getUsername()) != null){
            return "redirect:/panel/user/add?exist=true";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/panel/crud?msg=user_added";
    }

    @GetMapping("/user/modify")
    public String modifyUserView(Model model, @RequestParam(name = "id") long id){
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        model.addAttribute("roles", ROLES);
        model.addAttribute("isActive", IS_ACTIVE);
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

    @GetMapping("category/add")
    public String addCategoryView(Model model){
        model.addAttribute("category", new Category());
        return "panel-category-add";
    }

    @PostMapping("/category/add")
    public String addCategoryHandler(Category category){
        categoryRepository.save(category);
        return "redirect:/panel?crud?msg=category_added";
    }

    @GetMapping("/category/modify")
    public String modifyCategoryView(Model model, @RequestParam(name = "id") long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if( category == null){
            return "redirect:/panel/crud?error=category_not_found";
        }
        model.addAttribute("category", category);
        return "panel-category-modify";
    }

    @PostMapping("/category/modify")
    public String modifyCategoryHandler(Category category){
        if( !categoryRepository.existsById(category.getId())){
            return "redirect:/panel/crud?error=category_not_found";
        }
        categoryRepository.save(category);
        return "redirect:/panel/crud?msg=category_modified";
    }

    @GetMapping("/category/delete")
    public String deleteCategoryConfirmationView(@RequestParam(name = "id") long id, Model model){
        Category category = categoryRepository.findById(id).orElse(null);
        if( category == null){
            return "redirect:/panel/crud?error=category_not_found";
        }
        model.addAttribute("category", category);
        return "panel-category-delete";
    }

    @PostMapping("/category/delete")
    public String deleteCategoryHandler(@RequestParam( name = "id") long id){
        Category category = categoryRepository.findById(id).orElse(null);
        if( category == null){
            return "redirect:/panel/crud?error=category_not_found";
        }
        categoryRepository.delete(category);
        return "redirect:/panel/crud?msg=category_deleted";
    }

    @GetMapping("/donation/modify")
    public String donationModifyView(@RequestParam(name = "id")long id,
                                     Model model){
        Donation donation = donationRepository.findById(id).orElse(null);
        if( donation == null){
            return "redirect:/panel/crud?error=donation_not_found";
        }
        model.addAttribute("donation", donation);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "panel-donation-modify";
    }

    @PostMapping("/donation/modify")
    public String donationModifyHandler(Donation donation){
        donationRepository.save(donation);
        return "redirect:/panel/crud?msg=donation_modified";
    }

    @GetMapping("/contactmsg/view")
    public String contactMSGView(Model model,
                                 @RequestParam("id")long id){
        ContactMSG contactMSG = contactMSGRepository.findById(id).orElse(null);
        if (contactMSG == null){
            return "redirect:/panel/crud?error=contactMSG_not_found";
        }
        model.addAttribute("contactMSG", contactMSG);
        return "panel-contactmsg-view";
    }

    @PostMapping("/contactmsg/view")
    public String contactMSGArchivedHandler(ContactMSG contactMSG){
        contactMSGRepository.save(contactMSG);
        return "redirect:/panel/crud?msg=contact_message_" + (contactMSG.getArchived() ? "archived" : "un_archived");
    }
}
