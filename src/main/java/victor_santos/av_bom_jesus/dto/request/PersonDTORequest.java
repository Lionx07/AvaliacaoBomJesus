package victor_santos.av_bom_jesus.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.List;

@Getter
@Setter
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProfessorDTORequest.class, name = "professor")
})
public abstract class PersonDTORequest {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Status status;
    private List<AddressDTORequest> addresses;
}
