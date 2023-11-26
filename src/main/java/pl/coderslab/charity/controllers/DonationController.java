package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.CategoryRepository;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.security.CurrentUser;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class DonationController {
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    @GetMapping("/form")
    public String donationFormView(Model model){
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("active","form");
        return "form";
    }

    @PostMapping("/form")
    public String donationForm(Donation donation, @AuthenticationPrincipal CurrentUser currentUser){
        donation.setIsPicked(false);
        donation.setCreated(LocalDateTime.now());
        donation = donationRepository.save(donation);
        currentUser.getUser().getDonations().add(donation);
        userRepository.save(currentUser.getUser());
        return "redirect:/";
    }
}
