package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TrashesEvents;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface TrashesEventsRepository extends JpaRepository<TrashesEvents, Long> {
    @Modifying
    void deleteTrashesEventsByTrashesId(@Param("trashId") Long trashId);
}