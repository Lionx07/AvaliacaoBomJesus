package victor_santos.av_bom_jesus.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "person_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Professor.class, name = "PROFESSOR"),
    @JsonSubTypes.Type(value = Student.class, name = "STUDENT")
})
@DiscriminatorValue("Student")
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