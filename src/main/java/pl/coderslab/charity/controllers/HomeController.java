package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.services.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionService institutionService;

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutionList", institutionService.getRandomFour());
        return "index";
    }
}
