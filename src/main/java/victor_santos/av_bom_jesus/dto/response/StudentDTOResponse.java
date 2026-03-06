package victor_santos.av_bom_jesus.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTOResponse extends PersonDTOResponse {
    private String studentNumber;
    private String photo;
}

