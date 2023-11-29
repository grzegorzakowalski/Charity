package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailServiceImpl;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/password")
public class PasswordRenewController {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/renew")
    public String forgotPasswordView(Model model,
                                     @RequestParam(name = "msg", required = false)String msg){
        if( msg != null){
            if(msg.equals("send")){
                msg = "Wiadomość email została wysłana na podany adres";
            }
            msg = msg.replaceAll("_", " ");
            model.addAttribute("msg", msg);
        }
        return "password-renew";
    }

    @PostMapping("/renew")
    public String forgotPassword(@RequestParam(name = "email", required = false)String email,
                                 HttpServletRequest request){
        if(email == null || email.isEmpty()){
            return "redirect:/password/renew?msg=podaj_adres_email";
        }
        User user = userRepository.findUserByUsername(email);
        if( user == null){
            return "redirect:/password/renew?msg=Nie_ma_takiego_konta";
        }
        user.setUUID(UUID.randomUUID().toString());
        emailService.sendSimpleMessage(email, "Resetowanie hasła","Kliknij w ten link aby przejść do formularza resetowania hasła: " + request.getRequestURL() + "/new?UUID=" + user.getUUID());
        userRepository.save(user);
        return "redirect:/password/renew?msg=send";
    }

    @GetMapping("/renew/new")
    public String createNewPasswordView(@RequestParam(name = "UUID", required = false)String uuid,
                                        Model model){
        if( uuid == null){
            return "redirect:/password/renew?msg=Nieznany_problem";
        }
        User user = userRepository.findUserByUUID(uuid);
        if( user == null){
            return "redirect:/password/renew?msg=Nie_ma_takiego_konta";
        }
        model.addAttribute("uuid",uuid);
        return "password-renew-new";
    }

    @PostMapping("/renew/new")
    public String createNewPassword(@RequestParam(name = "uuid")String uuid,
                                    @RequestParam(name = "password")String password){
        User user = userRepository.findUserByUUID(uuid);
        if( user == null){
            return "redirect:/password/renew?msg=Nieznany_problem";
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setUUID("");
        userRepository.save(user);
        return "redirect:/login";
    }
}
