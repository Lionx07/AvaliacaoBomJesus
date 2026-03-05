package victor_santos.av_bom_jesus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import victor_santos.av_bom_jesus.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
