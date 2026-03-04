package victor_santos.av_bom_jesus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import victor_santos.av_bom_jesus.entity.Professor;
import victor_santos.av_bom_jesus.entity.Student;
import victor_santos.av_bom_jesus.enums.Status;
import victor_santos.av_bom_jesus.repository.PersonRepository;
import victor_santos.av_bom_jesus.service.exception.BadRequestException;
import victor_santos.av_bom_jesus.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Nested
    @DisplayName("Tests for addPerson")
    class AddPerson {

        @Test
        @DisplayName("Should create a Student with success")
        void shouldCreateAStudent() {
            var student = createStudentMock();
            // Mock do repositório
            when(personRepository.save(any(Student.class))).thenReturn(student);

            var output = personService.addPerson(student);

            assertNotNull(output);
            assertEquals("Maria Helena", output.getName());
            verify(personRepository, times(1)).save(any());
        }

        @Test
        @DisplayName("Should throw BadRequestException when student is empty")
        void shouldThrowExceptionWhenStudentIsEmpty() {
            var input = new Student();
            // Supondo que isEmpty() checa se o nome é nulo
            assertThrows(BadRequestException.class, () -> personService.addPerson(input));
        }

        @Test
        @DisplayName("Should throw BadRequestException when object is null")
        void shouldThrowExceptionWhenNull() {
            assertThrows(BadRequestException.class, () -> personService.addPerson(null));
        }
    }

    @Nested
    @DisplayName("Tests for getPerson")
    class GetPerson {

        @Test
        @DisplayName("Should return a list of persons")
        void shouldReturnList() {
            when(personRepository.findAll()).thenReturn(List.of(new Student(), new Professor()));

            var output = personService.getPersons();

            assertEquals(2, output.size());
            verify(personRepository).findAll();
        }

        @Test
        @DisplayName("Should return person by id")
        void shouldReturnPersonById() {
            var student = createStudentMock();
            when(personRepository.findById(1L)).thenReturn(Optional.of(student));

            var output = personService.getPersonById(1L);

            assertNotNull(output);
            verify(personRepository).findById(1L);
        }

        @Test
        @DisplayName("Should throw NotFoundException when id not exists")
        void shouldThrowNotFound() {
            when(personRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> personService.getPersonById(1L));
        }
    }

    @Nested
    @DisplayName("Tests for delete methods")
    class DeletePerson {

        @Test
        @DisplayName("Should delete person with success")
        void shouldDelete() {
            when(personRepository.existsById(1L)).thenReturn(true);

            assertDoesNotThrow(() -> personService.deletePerson(1L));
            verify(personRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Should soft delete (DISABLE) person")
        void shouldSoftDelete() {
            var student = createStudentMock();
            student.setStatus(Status.ACTIVE);
            when(personRepository.findById(1L)).thenReturn(Optional.of(student));

            personService.softDeletePerson(1L);

            assertEquals(Status.DISABLE, student.getStatus());
            verify(personRepository).save(student);
        }
    }

    @Nested
    @DisplayName("Tests for updatePerson")
    class UpdatePerson {

        @Test
        @DisplayName("Should update Student data correctly")
        void shouldUpdateStudent() {
            Long id = 1L;
            Student existing = createStudentMock();
            Student updatedData = createStudentMock();
            updatedData.setName("Maria Silva");

            when(personRepository.findById(id)).thenReturn(Optional.of(existing));
            when(personRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

            var result = (Student) personService.updatePerson(id, updatedData);

            assertEquals("Maria Silva", result.getName());
            verify(personRepository).save(any());
        }

        @Test
        @DisplayName("Should throw BadRequest when update object is null")
        void shouldThrowBadRequest() {
            assertThrows(BadRequestException.class, () -> personService.updatePerson(1L, null));
        }
    }

    // Helper method para evitar repetição de código
    private Student createStudentMock() {
        return new Student(
            "Maria Helena",
            "(44) 99516-6159",
            Status.ACTIVE,
            new ArrayList<>(),
            "maria-helena@email.com",
            "RA09765",
            "default"
        );
    }
}