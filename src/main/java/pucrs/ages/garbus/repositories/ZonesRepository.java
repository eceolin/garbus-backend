package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Zones;
import pucrs.ages.garbus.repositories.sql.ZonesSql;

@Repository
public interface ZonesRepository extends JpaRepository<Zones, Long> {

    @Query(
            value = ZonesSql.findZoneDescriptionByTrashIdSql,
            nativeQuery = true
    )
    String findZoneDescriptionByTrashId(@Param("zoneIdFromTrash") Long zoneIdFromTrash);
}
