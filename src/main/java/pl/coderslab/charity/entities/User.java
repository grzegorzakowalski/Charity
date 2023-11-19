package pl.coderslab.charity.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username; // email as username
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Donation> donations;
    private String role;
}
