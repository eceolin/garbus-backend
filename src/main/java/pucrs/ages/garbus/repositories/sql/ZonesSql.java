package pucrs.ages.garbus.repositories.sql;

public class ZonesSql {
    public final static String findZoneDescriptionByTrashIdSql =
            "SELECT DESCRIPTION FROM ZONES WHERE ZONES.ID = :zoneIdFromTrash";
}
