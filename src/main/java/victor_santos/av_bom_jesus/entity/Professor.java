package victor_santos.av_bom_jesus.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import victor_santos.av_bom_jesus.enums.Status;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("Professor")
public class Professor extends Person {

    private Double salary;

    public Professor(
        String name,
        String phoneNumber,
        String emailAddress,
        Status status,
        List<Address> addresses,
        Double salary
    ) {
        super(name, phoneNumber, emailAddress, status, addresses);
        this.salary = salary;
    }
}