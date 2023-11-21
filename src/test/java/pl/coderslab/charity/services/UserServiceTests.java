package pl.coderslab.charity.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    public User getUserWithNDonationsAndQuantityNTimesTwo(int n){
        User user = new User();
        List<Donation> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Donation donation = new Donation();
            donation.setQuantity(2);
            list.add(donation);
        }
        user.setDonations(list);
        return user;
    }
    @Test
    public void givenUserWithNoDonations_getUserDonationsAmount_ShouldGiveZero(){
        assertEquals(0, userService.getUserDonationsAmount(getUserWithNDonationsAndQuantityNTimesTwo(0)));
    }

    @Test
    public void givenUserWithThreeDonations_getUserDonationsAmount_ShouldGiveThree(){
        assertEquals(3, userService.getUserDonationsAmount(getUserWithNDonationsAndQuantityNTimesTwo(3)));
    }

    @Test
    public void givenNullAsUser_getUserDonationsAmount_ShouldThrowException(){
        try {
            userService.getUserDonationsAmount(null);
            fail();
        } catch (Exception ignored){
        }
    }

    @Test
    public void givenUserWithNoDonations_getUserBagsDonatedAmount_ShouldGiveZero(){
        assertEquals(0, userService.getUserBagsDonatedAmount(getUserWithNDonationsAndQuantityNTimesTwo(0)));
    }

    @Test
    public void givenUserWithThreeDonations_getUserBagsDonatedAmount_ShouldGiveSix(){
        assertEquals(6, userService.getUserBagsDonatedAmount(getUserWithNDonationsAndQuantityNTimesTwo(3)));
    }

    @Test
    public void givenNullAsUser_getUserBagsDonatedAmount_ShouldThrowException(){
        try {
            userService.getUserBagsDonatedAmount(null);
            fail();
        } catch (Exception ignored){
        }
    }
}
