package pl.coderslab.charity.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
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

    public User getUserWithGivenId(Long id){
        User user = new User();
        user.setId(id);
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

    @Test
    public void givenUser_getAllWithout_shouldGiveListWithoutUser(){
        List<User> list = new ArrayList<>();
        User old = getUserWithGivenId(1L);
        User toDelete = getUserWithGivenId(1L);
        list.add(old);
        Mockito.when(userRepository.findAll()).thenReturn(list);
        assertEquals(0, userService.getAllWithout(toDelete).size());
    }

    @Test
    public void givenUserNotFromList_getAllWithout_ShouldGiveFullList(){
        List<User> list = new ArrayList<>();
        User old = getUserWithGivenId(1L);
        User toDelete = getUserWithGivenId(2L);
        list.add(old);
        Mockito.when(userRepository.findAll()).thenReturn(list);
        assertEquals(1, userService.getAllWithout(toDelete).size());
    }

    @Test
    public void givenMoreThanOne_getAllWithout_ShouldGiveListWithoutAllGiven(){
        List<User> list = new ArrayList<>();
        for (long i = 0L; i < 9; i++){
            list.add(getUserWithGivenId(i));
        }
        Mockito.when(userRepository.findAll()).thenReturn(list);
        User user1 = getUserWithGivenId(2L);
        User user2 = getUserWithGivenId(7L);
        assertEquals(7, userService.getAllWithout(user1,user2).size());
    }
}
