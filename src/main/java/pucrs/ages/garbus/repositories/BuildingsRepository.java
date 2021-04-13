package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Buildings;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface BuildingsRepository extends JpaRepository<Buildings, Long> {
}