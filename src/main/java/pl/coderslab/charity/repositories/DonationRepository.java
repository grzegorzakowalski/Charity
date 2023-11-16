package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entities.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    /**
     * @return Amount of bags or null if db is empty.
     */
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer getAmountOfBags();

    /**
     * @return Amount of donations.
     */
    @Query("SELECT COUNT(d) FROM Donation d")
    Integer getAmountOfDonations();
}
