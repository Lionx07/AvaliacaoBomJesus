package victor_santos.av_bom_jesus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.*;
import victor_santos.av_bom_jesus.enums.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "person_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Professor.class, name = "PROFESSOR"),
    @JsonSubTypes.Type(value = Student.class, name = "STUDENT")
})
@Table(name = "tb_person")
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private List<Address> addresses = new ArrayList<>();

    protected Person(String name, String phoneNumber, String emailAddress, Status status, List<Address> addresses) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;

        if (addresses != null) {
            addAddresses(addresses);
        }
    }

    public void addAddresses(List<Address> addresses) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.addAll(addresses);
        addresses.forEach(address -> address.setPerson(this));
    }

    public boolean isEmpty() {
        return
            (name == null || name.isBlank()) &&
            (emailAddress == null || emailAddress.isBlank());
    }
}