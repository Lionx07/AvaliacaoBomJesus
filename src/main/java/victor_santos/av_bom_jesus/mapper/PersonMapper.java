package victor_santos.av_bom_jesus.mapper;

import org.mapstruct.Mapper;
import victor_santos.av_bom_jesus.dto.request.PersonDTORequest;
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
    StudentDTOResponse toDtoResponse(Student student);
    ProfessorDTOResponse toDtoResponse(Professor professor);
    AddressDTOResponse toDtoResponse(Address address);

    default PersonDTOResponse toDtoResponse(Person person) {
        if (person == null) return null;

        if (person instanceof Student) return toDtoResponse((Student) person);
        if (person instanceof Professor) return toDtoResponse((Professor) person);
        return null;
    }

    // --- Response List Map ---
    default List<PersonDTOResponse> toResponseList(List<Person> persons) {
        if (persons == null) return null;

        return persons.stream()
                .map(person -> {
                    if (person instanceof Student) return toDtoResponse((Student) person);
                    if (person instanceof Professor) return toDtoResponse((Professor) person);
                    return null;
                })
                .collect(Collectors.toList());
    }

    // --- Request Map ---
    Student toEntity(StudentDTORequest dto);
    Professor toEntity(ProfessorDTORequest dto);

    default Person toEntity(PersonDTORequest dto) {
        if (dto == null) return null;

        if (dto instanceof StudentDTORequest) return toEntity((StudentDTORequest) dto);
        if (dto instanceof ProfessorDTORequest) return toEntity((ProfessorDTORequest) dto);

        return null;
    }
}
