package pl.coderslab.charity.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact_messages")
public class ContactMSG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private String surname;
    private String msg;
    private Boolean archived;
}
