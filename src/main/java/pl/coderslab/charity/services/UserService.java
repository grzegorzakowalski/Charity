package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public int getUserDonationsAmount(User user){
        return user.getDonations().size();
    }

    public int getUserBagsDonatedAmount(User user){
        return user.getDonations().stream()
                .mapToInt(Donation::getQuantity)
                .reduce(0, Integer::sum);
    }
}
