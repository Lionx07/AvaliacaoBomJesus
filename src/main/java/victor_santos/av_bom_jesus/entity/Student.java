package victor_santos.av_bom_jesus.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {

    private String studentNumber;
    private String photo;

    public Student(
        String name,
        String phoneNumber,
        Status status,
        List<Address> addresses,
        String emailAddress,
        String studentNumber,
        String photo
    ) {
        super(name, phoneNumber, emailAddress, status, addresses);
        this.studentNumber = studentNumber;
        this.photo = photo;
    }
}