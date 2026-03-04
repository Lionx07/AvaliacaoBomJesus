package victor_santos.av_bom_jesus.mapper;

import org.mapstruct.Mapper;
import victor_santos.av_bom_jesus.dto.request.ProfessorDTORequest;
import victor_santos.av_bom_jesus.dto.request.StudentDTORequest;
import victor_santos.av_bom_jesus.dto.response.AddressDTOResponse;
import victor_santos.av_bom_jesus.dto.response.PersonDTOResponse;
import victor_santos.av_bom_jesus.dto.response.ProfessorDTOResponse;
import victor_santos.av_bom_jesus.dto.response.StudentDTOResponse;
import victor_santos.av_bom_jesus.entity.Address;
import victor_santos.av_bom_jesus.entity.Person;
import victor_santos.av_bom_jesus.entity.Professor;
import victor_santos.av_bom_jesus.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    // --- Response Map ---
    StudentDTOResponse toDto(Student student);
    ProfessorDTOResponse toDto(Professor professor);
    AddressDTOResponse toDto(Address address);

    // --- Response List Map ---
    default List<PersonDTOResponse> toResponseList(List<Person> persons) {
        if (persons == null) return null;

        return persons.stream()
                .map(person -> {
                    if (person instanceof Student) return toDto((Student) person);
                    if (person instanceof Professor) return toDto((Professor) person);
                    return null;
                })
                .collect(Collectors.toList());
    }

    // --- Request Map ---
    Student toEntity(StudentDTORequest dto);
    Professor toEntity(ProfessorDTORequest dto);
}
