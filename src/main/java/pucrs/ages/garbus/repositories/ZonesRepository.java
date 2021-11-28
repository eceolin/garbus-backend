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
            value = ZonesSql.FIND_ZONE_DESCRIPTION_BY_TRASH_ID_SQL,
            nativeQuery = true
    )
    String findZoneDescriptionByTrashId(@Param("zoneIdFromTrash") Long zoneIdFromTrash);

    @Query(
            value = ZonesSql.COUNT_TRASHES_BY_ID_ZONE,
            nativeQuery = true
    )
    int countTrashesByIdZone(@Param("zoneId") Long zoneId);

    @Query(
            value = ZonesSql.COUNT_BUILDINGS_BY_ID_ZONE,
            nativeQuery = true
    )
    int countBuildingsByIdZone(@Param("zoneId") Long zoneId);
}
