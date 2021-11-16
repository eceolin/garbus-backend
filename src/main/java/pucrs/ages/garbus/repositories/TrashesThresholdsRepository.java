package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TrashesThreshold;
import pucrs.ages.garbus.repositories.sql.TrashesThresholdsSql;

import java.util.List;

@Repository
public interface TrashesThresholdsRepository extends JpaRepository<TrashesThreshold, Long> {
    @Query(
            value = TrashesThresholdsSql.FIND_ALL_THRESHOLDS_SQL,
            nativeQuery = true
    )
    List<TrashesThreshold> findAllThresholds();

    @Query(
            value = TrashesThresholdsSql.FIND_THRESHOLDS_BY_TRASH_ID_SQL,
            nativeQuery = true
    )
    List<TrashesThreshold> findThresholdsByTrashId(@Param("trashId") Long trashId);

    @Query(
            value = TrashesThresholdsSql.FIND_THRESHOLDS_MAX_OCCUPATION_BY_TRASH_ID_SQL,
            nativeQuery = true
    )
    TrashesThreshold findThresholdsMaxOccupationByTrashId(@Param("trashId") Long trashId);

    @Modifying
    void deleteTrashesThresholdsByTrashesId(@Param("trashId") Long trashId);

}