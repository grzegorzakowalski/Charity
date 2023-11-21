package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.services.InstitutionService;

@Controller
@RequiredArgsConstructor
public class InstitutionsController {
    private final InstitutionService institutionService;

    @GetMapping("/institutions")
    private String allInstitutionsView(Model model){
        model.addAttribute("pairList", institutionService.getAllInstitutionsAsPairs());
        model.addAttribute("active", "institutions");
        return "institutions";
    }
}
