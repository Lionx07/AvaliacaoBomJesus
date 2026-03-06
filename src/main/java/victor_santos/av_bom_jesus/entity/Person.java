package victor_santos.av_bom_jesus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
@Table(name = "tb_person")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    private String phoneNumber;

    @Email(message = "E-mail invalid")
    @NotBlank(message = "E-mail is required")
    @Column(unique = true)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    public Person(String name, String phoneNumber, String emailAddress, Status status, List<Address> addresses) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.addresses = addresses;
    }

    public void addAddresses(List<Address> incomingAddresses) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        for (Address addr : incomingAddresses) {
            addr.setPerson(this);
            this.addresses.add(addr);
        }
    }
}