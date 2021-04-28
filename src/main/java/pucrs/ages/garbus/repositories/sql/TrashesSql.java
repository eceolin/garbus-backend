package pucrs.ages.garbus.repositories.sql;

public class TrashesSql {

    public final static String findByZonesIdAndBuildingsInZonesIdSQL =
            " SELECT ID, BRAND, DESCRIPTION, CAPACITY, OCCUPATION, LONGITUDE, LATITUDE, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE"
                +" FROM ( "
                +" 	SELECT id, BRAND, DESCRIPTION, CAPACITY, OCCUPATION, LONGITUDE, LATITUDE, ID_STATUS, ID_TYPE, ID_BUILDING, ID_ZONE, building, zone FROM ( "
                +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.LONGITUDE, t.LATITUDE, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, p.id AS building, z.id AS zone, 'em prédio' AS tipo"
                +" 		FROM trashes t"
                +" 		JOIN buildings p ON t.id_building = p.id"
                +" 		JOIN zones z ON p.id_zone = z.id"
                +" 		UNION"
                +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.LONGITUDE, t.LATITUDE, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, NULL AS building, z.id AS zone, 'em zone' AS tipo"
                +" 		FROM trashes t"
                +" 		JOIN zones z ON t.id_zone = z.id"
                +" 		UNION"
                +" 		SELECT t.id, t.BRAND, t.DESCRIPTION, t.CAPACITY, t.OCCUPATION, t.LONGITUDE, t.LATITUDE, t.ID_STATUS, t.ID_TYPE, t.ID_BUILDING, t.ID_ZONE, NULL AS building, z.id AS zone, 'solta' AS tipo"
                +" 		FROM trashes t"
                +" 		LEFT JOIN buildings p ON t.id_building = p.id"
                +" 		LEFT JOIN zones z ON t.id_zone = z.id"
                +" 		WHERE p.id IS NULL AND z.id IS NULL"
                +" 	) a "
                +" ) c "
            +" WHERE ZONE = :zoneId";
}
