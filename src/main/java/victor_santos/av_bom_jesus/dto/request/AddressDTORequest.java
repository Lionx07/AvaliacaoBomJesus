package victor_santos.av_bom_jesus.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTORequest {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
