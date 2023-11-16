package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.services.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutionList", institutionService.getRandomFour());
        Integer bagsAmount = donationRepository.getAmountOfBags();
        model.addAttribute("bagsAmount",bagsAmount == null? 0 : bagsAmount);
        return "index";
    }
}
