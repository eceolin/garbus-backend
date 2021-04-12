package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Predios;

/**
 * JPA repository to Predios entities.
 *
 */
@Repository
public interface PrediosRepository extends JpaRepository<Predios, Long> {
}