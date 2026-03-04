package victor_santos.av_bom_jesus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import victor_santos.av_bom_jesus.entity.Person;
import victor_santos.av_bom_jesus.entity.Professor;
import victor_santos.av_bom_jesus.entity.Student;
import victor_santos.av_bom_jesus.entity.enums.Status;
import victor_santos.av_bom_jesus.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(UUID id) {
        return Optional.of(personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found")));
    }

    @Transactional
    public Person addPerson(Person obj) {
        if (obj == null)
            throw new IllegalArgumentException("Object cannot be null");

        return personRepository.save(obj);
    }

    @Transactional
    public void deletePerson(UUID id) {
        if (!personRepository.existsById(id))
            throw new RuntimeException("Person not found");

        personRepository.deleteById(id);
    }

    @Transactional
    public void softDeletePerson(UUID id) {
        Person existPerson = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        existPerson.setStatus(Status.DISABLE);

        personRepository.save(existPerson);
    }

    @Transactional
    public Person updatePerson(UUID id, Person person) {
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null");

        return personRepository.findById(id).map(personToUpdate -> {
            if (personToUpdate instanceof Professor && person instanceof Professor) {
                updateProfessor((Professor)personToUpdate, (Professor) person);
            }
            else if (personToUpdate instanceof Student && person instanceof Student) {
                updateStudent((Student)personToUpdate, (Student) person);
            }
            return personRepository.save(personToUpdate);
        }).orElseThrow(() -> new RuntimeException("Person not found"));
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