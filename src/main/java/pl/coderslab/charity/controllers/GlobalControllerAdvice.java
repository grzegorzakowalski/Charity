package pl.coderslab.charity.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entities.ContactMSG;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public void addCommonObject(Model model) {
        model.addAttribute("contactMSG", new ContactMSG());
    }
}

