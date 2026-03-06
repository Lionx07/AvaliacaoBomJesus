package victor_santos.av_bom_jesus.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTORequest extends PersonDTORequest {
    private String studentNumber;
    private String photo;
}
