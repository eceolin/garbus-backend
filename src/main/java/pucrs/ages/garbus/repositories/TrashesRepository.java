package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Trashes;
import java.util.List;

@Repository
public interface TrashesRepository extends JpaRepository<Trashes, Long> {

    List<Trashes> findByZonesId(Long zoneId);

    @Query(
        value =
             " SELECT ID, BRAND, DESCRIPTION, CAPACITY, OCCUPATION, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE"
            +" FROM ( "
            +" 	SELECT * FROM ( "
            +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, p.id AS building, z.id AS zone, 'em prédio' AS tipo"
            +" 		FROM trashes t"
            +" 		JOIN buildings p ON t.id_building = p.id"
            +" 		JOIN zones z ON p.id_zone = z.id"
            +" 		UNION"
            +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, NULL AS building, z.id AS zone, 'em zone' AS tipo"
            +" 		FROM trashes t"
            +" 		JOIN zones z ON t.id_zone = z.id"
            +" 		UNION"
            +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, NULL AS building, z.id AS zone, 'solta' AS tipo"
            +" 		FROM trashes t"
            +" 		LEFT JOIN buildings p ON t.id_building = p.id"
            +" 		LEFT JOIN zones z ON t.id_zone = z.id"
            +" 		WHERE p.id IS NULL AND z.id IS NULL"
            +" 	) a "
            +" ) c "
            +" WHERE ZONE = :zoneId",
        nativeQuery = true
    )
    List<Trashes> findByZonesIdAndBuildingsInZonesId(@Param("zoneId") Long zoneId);
}