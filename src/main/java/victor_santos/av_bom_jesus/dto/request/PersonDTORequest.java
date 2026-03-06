package victor_santos.av_bom_jesus.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProfessorDTORequest.class, name = "professor"),
    @JsonSubTypes.Type(value = StudentDTORequest.class, name = "student")
})
public abstract class PersonDTORequest {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Status status;
    private List<AddressDTORequest> addresses;
}
