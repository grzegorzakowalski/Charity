package pl.coderslab.charity.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.pojo.InstitutionPair;
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

    @Test
    public void whenDataBaseIsEmpty_getAllInstitutionsAsPairs_ShouldReturnListWithOnePair(){
        Mockito.when(institutionRepository.findAll()).thenReturn(new ArrayList<>());
        List<InstitutionPair> actual = institutionService.getAllInstitutionsAsPairs();
        assertEquals(1, actual.size());
    }

    @Test
    public void whenDataInDataBaseIsOddAmount_getAllInstitutionsAsPairs_ShouldFillLastWithFillerInstitution(){
        Institution institution = new Institution();
        List<Institution> list = new ArrayList<>();
        list.add(institution);
        Mockito.when(institutionRepository.findAll()).thenReturn(list);
        List<InstitutionPair> actual = institutionService.getAllInstitutionsAsPairs();
        assertEquals(2, actual.get(0).getInstitutionPair().size());
    }
    @Test
    public void whenDataInDataBaseIsEvenAmount_getAllInstitutionsAsPairs_ShouldReturnAllDataPaired(){
        List<Institution> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Institution());
        }
        Mockito.when(institutionRepository.findAll()).thenReturn(list);
        List<InstitutionPair> actual = institutionService.getAllInstitutionsAsPairs();
        assertEquals(2, actual.get(actual.size() - 1).getInstitutionPair().size() );
    }

}
