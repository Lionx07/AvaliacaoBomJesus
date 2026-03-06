package victor_santos.av_bom_jesus.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTORequest extends PersonDTORequest {
    private Double salary;
}
