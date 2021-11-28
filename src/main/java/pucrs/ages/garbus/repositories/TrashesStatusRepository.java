package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TrashesStatus;

@Repository
public interface TrashesStatusRepository extends JpaRepository<TrashesStatus, Long> {
}