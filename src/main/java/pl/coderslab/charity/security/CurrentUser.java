package pl.coderslab.charity.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CurrentUser extends User {

    private final pl.coderslab.charity.entities.User user;
    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, pl.coderslab.charity.entities.User user) {
        super(username, password, user.isActive(), true, true, true,  authorities);
        this.user = user;
    }
}
