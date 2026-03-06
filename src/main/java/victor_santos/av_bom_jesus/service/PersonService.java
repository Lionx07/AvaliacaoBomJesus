package victor_santos.av_bom_jesus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victor_santos.av_bom_jesus.entity.Address;
import victor_santos.av_bom_jesus.entity.Person;
import victor_santos.av_bom_jesus.entity.Professor;
import victor_santos.av_bom_jesus.entity.Student;
import victor_santos.av_bom_jesus.enums.Status;
import victor_santos.av_bom_jesus.repository.PersonRepository;
import victor_santos.av_bom_jesus.repository.ProfessorRepository;
import victor_santos.av_bom_jesus.repository.StudentRepository;
import victor_santos.av_bom_jesus.service.exception.BadRequestException;
import victor_santos.av_bom_jesus.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<Student> getStudents() {
        return studentRepository.findAll().stream()
            .filter(p -> p.getStatus().equals(Status.ACTIVE))
            .collect(Collectors.toList());
    }

    public List<Professor> getProfessors() {
        return professorRepository.findAll().stream()
            .filter(p -> p.getStatus().equals(Status.ACTIVE))
            .collect(Collectors.toList());
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
    }

    public Person addPerson(Person obj) {
        if (obj == null)
            throw new BadRequestException("Object cannot be null");

        if (obj.getAddresses() != null) {
            List<Address> incomingAddresses = new ArrayList<>(obj.getAddresses());

            obj.getAddresses().clear();

            for (Address addr : incomingAddresses) {
                addr.setId(null);
                addr.setPerson(obj);
                obj.getAddresses().add(addr);
            }
        }

        return personRepository.save(obj);
    }

    @Transactional
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id))
            throw new NotFoundException("Person not found");

        personRepository.deleteById(id);
    }

    @Transactional
    public void softDeletePerson(Long id) {
        Person existPerson = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        existPerson.setStatus(Status.DISABLE);

        personRepository.save(existPerson);
    }

    @Transactional
    public Person updatePerson(Long id, Person person) {
        if (person == null)
            throw new BadRequestException("Person cannot be null");

        return personRepository.findById(id).map(personToUpdate -> {
            if (personToUpdate instanceof Professor && person instanceof Professor) {
                updateProfessor((Professor)personToUpdate, (Professor) person);
            }
            else if (personToUpdate instanceof Student && person instanceof Student) {
                updateStudent((Student)personToUpdate, (Student) person);
            }
            return personRepository.save(personToUpdate);
        }).orElseThrow(() -> new NotFoundException("Person not found"));
    }

    private void updatePersonData(Person personToUpdate, Person person) {
        personToUpdate.setName(person.getName());
        personToUpdate.setPhoneNumber(person.getPhoneNumber());
        personToUpdate.setEmailAddress(person.getEmailAddress());
        personToUpdate.setStatus(person.getStatus());
        personToUpdate.addAddresses(person.getAddresses());
    }

    private void updateProfessor(Professor professorToUpdate, Professor professor) {
        updatePersonData(professorToUpdate, professor);
        professorToUpdate.setSalary(professor.getSalary());
    }

    private void updateStudent(Student studentToUpdate, Student student) {
        updatePersonData(studentToUpdate, student);
        studentToUpdate.setStudentNumber(student.getStudentNumber());
        studentToUpdate.setPhoto(student.getPhoto());
    }
}