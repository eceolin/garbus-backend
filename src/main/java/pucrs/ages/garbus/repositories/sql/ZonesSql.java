package pucrs.ages.garbus.repositories.sql;

public class ZonesSql {
    public final static String findZoneDescriptionByTrashIdSql =
            "SELECT DESCRIPTION FROM ZONES WHERE ZONES.ID = :zoneIdFromTrash";

    public final static String countTrashesByIdZone = "" +
            "SELECT COUNT(T.*)\n" +
            "FROM TRASHES T\n" +
            "JOIN ZONES Z\n" +
            "WHERE Z.ID = :zoneId";

    public final static String countBuildingsByIdZone = "" +
            "SELECT COUNT(B.*)\n" +
            "FROM BUILDINGS B\n" +
            "JOIN ZONES Z\n" +
            "WHERE Z.ID = :zoneId";
}
