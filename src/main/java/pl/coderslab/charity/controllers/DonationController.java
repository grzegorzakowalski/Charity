package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;

@Controller
@RequiredArgsConstructor
public class DonationController {
    private final DonationRepository donationRepository;

    @GetMapping("/form")
    public String donationFormView(Model model){
        model.addAttribute("donation",new Donation());
        return "form";
    }
}
