package victor_santos.av_bom_jesus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import victor_santos.av_bom_jesus.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
