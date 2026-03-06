package victor_santos.av_bom_jesus.dto.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTOResponse extends PersonDTOResponse{
    private Double salary;
}

