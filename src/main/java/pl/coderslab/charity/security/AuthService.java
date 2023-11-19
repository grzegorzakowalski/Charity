package pl.coderslab.charity.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.Set;
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if( user == null){
            throw new UsernameNotFoundException("Nie istnieje użytkownik o loginie:" + username);
        }
        return new CurrentUser(user.getUsername(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole())), user);
    }
}
