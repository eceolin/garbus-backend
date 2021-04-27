package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.repositories.sql.TrashesSql;

import java.util.List;

@Repository
public interface TrashesRepository extends JpaRepository<Trashes, Long> {

    List<Trashes> findByZonesId(Long zoneId);

    @Query(
        value = TrashesSql.findByZonesIdAndBuildingsInZonesIdSQL,
        nativeQuery = true
    )
    List<Trashes> findByZonesIdAndBuildingsInZonesId(@Param("zoneId") Long zoneId);

    @Query(
            value =
                    "SELECT * FROM TRASHES WHERE TRASHES.ID_BUILDING = :buildingId",
            nativeQuery = true
    )
    List<Trashes> findByBuildingId(Long buildingId);

    @Query(
            value =
                    "SELECT * FROM TRASHES WHERE TRASHES.ID = :trashId",
            nativeQuery = true
    )
    List<Trashes> findDetailsByTrashId(Long trashId);
}