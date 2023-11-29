package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailServiceImpl;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("active","login");
        return "login";
    }

    @GetMapping("/password-renew")
    public String forgotPasswordView(Model model, @RequestParam(name = "msg", required = false)String msg){
        model.addAttribute("msg", msg != null ? msg.replaceAll("_", " ") : null);
        return "password-renew";
    }

    @PostMapping("/password-renew")
    public String forgotPassword(@RequestParam(name = "email", required = false)String email,
                                 HttpServletRequest request){
        if(email == null || email.isEmpty()){
            return "redirect:/password-renew?msg=podaj_adres_email";
        }
        User user = userRepository.findUserByUsername(email);
        if( user == null){
            return "redirect:/password-renew?msg=Nie_ma_takiego_konta";
        }
        user.setUUID(UUID.randomUUID().toString());
        emailService.sendSimpleMessage(email, "Resetowanie hasła","Kliknij w ten link aby przejść do formularza resetowania hasła: " + request.getRequestURL() + "?UUID=" + user.getUUID());
        return "redirect:/password-renew?msg=Wysłano_email_z_linkiem_do_zresetowania_hasła";
    }
}
