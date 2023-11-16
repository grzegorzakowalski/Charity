package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    /**
     * Gets all institutions from database, and returns either four random entities or all entities if there are less than 5.
     * @return List of Institutions with maximum size 4.
     */
    public List<Institution> getRandomFour(){
        List<Institution> all = institutionRepository.findAll();
        if( all.size() < 5){
            return all;
        }
        List<Institution> result = new ArrayList<>();
        Random random = new Random();
        while (result.size() < 4){
            int randomIndex = random.nextInt(all.size());
            result.add(all.get(randomIndex));
            all.remove(randomIndex);
        }
        return result;
    }
}
