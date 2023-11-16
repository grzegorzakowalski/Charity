package pl.coderslab.charity.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InstitutionServiceTests {
    @Mock
    private InstitutionRepository institutionRepository;

    @InjectMocks
    private InstitutionService institutionService;

    @Test
    public void whenDataBaseIsEmpty_getRandomFour_ShouldReturnFour(){
        Mockito.when(institutionRepository.findAll()).thenReturn(new ArrayList<>());
        List<Institution> actual = institutionService.getRandomFour();
        assertEquals(4, actual.size());
    }

    @Test
    public void whenInDataBaseIsLessThenFifeRows_getRandomFour_ShouldGiveFour(){
        List<Institution> dataBase = new ArrayList<>();
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        Mockito.when(institutionRepository.findAll()).thenReturn(dataBase);
        List<Institution> actual = institutionService.getRandomFour();
        assertEquals(4, actual.size());
    }

    @Test
    public void whenInDataBaseIsMoreThenFour_getRandomFour_ShouldGiveFour(){
        List<Institution> dataBase = new ArrayList<>();
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        dataBase.add(new Institution());
        Mockito.when(institutionRepository.findAll()).thenReturn(dataBase);
        List<Institution> actual = institutionService.getRandomFour();
        assertEquals(4, actual.size());
    }

}
