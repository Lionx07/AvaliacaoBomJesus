package victor_santos.av_bom_jesus.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTOResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
