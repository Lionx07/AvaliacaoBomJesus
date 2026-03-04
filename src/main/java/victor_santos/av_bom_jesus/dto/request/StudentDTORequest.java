package victor_santos.av_bom_jesus.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTORequest extends PersonDTORequest {
    private String studentNumber;
    private String photo;
}
