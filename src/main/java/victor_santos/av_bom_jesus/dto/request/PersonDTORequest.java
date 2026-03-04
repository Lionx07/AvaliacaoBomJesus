package victor_santos.av_bom_jesus.dto.request;

import lombok.Getter;
import lombok.Setter;
import victor_santos.av_bom_jesus.enums.Status;

import java.util.List;

@Getter
@Setter
public abstract class PersonDTORequest {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private Status status;
    private List<AddressDTORequest> addresses;
}
