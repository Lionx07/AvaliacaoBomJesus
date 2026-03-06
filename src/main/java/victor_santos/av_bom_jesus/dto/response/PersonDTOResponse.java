package victor_santos.av_bom_jesus.dto.response;

import lombok.Getter;
import lombok.Setter;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.List;

@Getter
@Setter
public abstract class PersonDTOResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Status status;
    private List<AddressDTOResponse> addresses;
}
