package pl.coderslab.charity.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.pojo.InstitutionPair;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    /**
     * Gets all institutions from database, and returns either four random entities or all entities that where in database with fillers up to 4.
     * @return List of Institutions with size 4.
     */
    public List<Institution> getRandomFour(){
        List<Institution> all = institutionRepository.findAll();
        if( all.size() < 5){
            while(all.size() < 4){
                all.add(getFillerInstitution());
            }
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

    /**
     * @return Institution filled with text encouraging to join as institution.
     */
    private Institution getFillerInstitution(){
        return Institution.builder().name("Dołącz do grona naszych fundacji!").description("Skontaktuj się z nami i dołącz do grona fundacji.").build();
    }

    /**
     * Gets all institutions from database. Puts this data into List of paired elements. If any pair wouldn't be full it fills it with filler. If db is empty returns list with one pair of fillers.
     * @return List of paired institutions.
     */
    public List<InstitutionPair> getAllInstitutionsAsPairs(){
        List<InstitutionPair> institutionPairs = new ArrayList<>();
        List<Institution> all = institutionRepository.findAll();
        if(all.isEmpty()){
            InstitutionPair institutionPair = new InstitutionPair();
            institutionPair.getInstitutionPair().add(getFillerInstitution());
            institutionPair.getInstitutionPair().add(getFillerInstitution());
            institutionPairs.add(institutionPair);
        } else {
            if ( all.size() == 1){
                InstitutionPair tmp = new InstitutionPair();
                tmp.getInstitutionPair().add(all.get(0));
                tmp.getInstitutionPair().add(getFillerInstitution());
                institutionPairs.add(tmp);
            } else {
                for (int i = 1; i <= all.size(); i+=2) {
                    InstitutionPair tmp = new InstitutionPair();
                    tmp.getInstitutionPair().add(all.get(i - 1));
                    if( i != all.size()){
                        tmp.getInstitutionPair().add(all.get(i));
                    }
                    institutionPairs.add(tmp);
                }
                if (institutionPairs.get(institutionPairs.size() - 1).getInstitutionPair().size() == 1){
                    institutionPairs.get(institutionPairs.size() - 1).getInstitutionPair().add(getFillerInstitution());
                }
            }
        }
        return institutionPairs;
    }
}
