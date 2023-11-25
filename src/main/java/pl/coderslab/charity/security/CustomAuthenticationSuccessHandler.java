package pl.coderslab.charity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userRepository.findUserByUsername(authentication.getName());
        if(Objects.equals(user.getRole(), "ROLE_ADMIN")){
            response.sendRedirect("/panel/crud");
        } else {
            response.sendRedirect("panel/user");
        }
    }
}
