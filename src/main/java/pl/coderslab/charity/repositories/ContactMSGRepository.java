package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entities.ContactMSG;

public interface ContactMSGRepository extends JpaRepository<ContactMSG, Long> {
    ContactMSG findAllByArchived(Boolean archived);
}
