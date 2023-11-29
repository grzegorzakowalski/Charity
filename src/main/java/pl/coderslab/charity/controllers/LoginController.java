package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("active","login");
        return "login";
    }

    @GetMapping("/password-renew")
    public String forgotPasswordView(Model model, @RequestParam(name = "msg", required = false)String msg){
        model.addAttribute("msg", msg);
        return "password-renew";
    }
}
