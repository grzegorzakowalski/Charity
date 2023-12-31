package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

    User findUserByUUID(String UUID);
}
