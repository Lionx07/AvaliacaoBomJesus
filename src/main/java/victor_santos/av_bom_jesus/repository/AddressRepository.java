package victor_santos.av_bom_jesus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import victor_santos.av_bom_jesus.entity.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
