package victor_santos.av_bom_jesus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import victor_santos.av_bom_jesus.entity.Person;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}