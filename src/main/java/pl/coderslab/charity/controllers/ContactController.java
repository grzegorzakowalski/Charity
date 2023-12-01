package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.ContactMSG;
import pl.coderslab.charity.repositories.ContactMSGRepository;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactMSGRepository contactMSGRepository;
    @GetMapping
    public String contactView(){
        return "contact";
    }

    @PostMapping
    public String contactMSGHandler(ContactMSG msg){
        contactMSGRepository.save(msg);
        return "redirect:/";
    }
}
