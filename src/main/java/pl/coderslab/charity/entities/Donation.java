package pl.coderslab.charity.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity; // amount of 60l bags in donation
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String street;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "pick_up_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;
    @Column(name = "pick_up_comment")
    private String pickUpComment;
    @Override
    public String toString(){
        StringBuilder categories = new StringBuilder("; ");
        for (Category category : this.categories) {
            categories.append(category.getName()).append(", ");
        }
        return "amount: " + quantity + categories + "for: " + institution.getName() + " when: " + pickUpDate.toString() + " " + pickUpTime.toString() + " where: "
                + city + " " + street + " nr: " + phoneNumber;
    }
}
