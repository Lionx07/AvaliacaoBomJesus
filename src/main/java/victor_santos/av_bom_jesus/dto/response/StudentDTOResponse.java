package victor_santos.av_bom_jesus.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOResponse extends PersonDTOResponse {
    private String studentNumber;
    private String photo;
}

