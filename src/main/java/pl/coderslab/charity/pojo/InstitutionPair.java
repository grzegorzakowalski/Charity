package pl.coderslab.charity.pojo;

import lombok.Data;
import pl.coderslab.charity.entities.Institution;

import java.util.ArrayList;
import java.util.List;

@Data
public class InstitutionPair {
    private List<Institution> institutionPair;

    public InstitutionPair(){
        institutionPair = new ArrayList<>();
    }
}
