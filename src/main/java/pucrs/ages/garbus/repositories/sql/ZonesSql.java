package pucrs.ages.garbus.repositories.sql;

public class ZonesSql {
    public ZonesSql() {
    }

    public static final String FIND_ZONE_DESCRIPTION_BY_TRASH_ID_SQL =
            "SELECT DESCRIPTION FROM ZONES WHERE ZONES.ID = :zoneIdFromTrash";

    public static final String COUNT_TRASHES_BY_ID_ZONE = ""
            + "SELECT COUNT(T.*) "
            + "FROM TRASHES T "
            + "JOIN ZONES Z "
            + "ON Z.ID = T.ID_ZONE "
            + "WHERE T.ID_ZONE = :zoneId";

    public static final String COUNT_BUILDINGS_BY_ID_ZONE = ""
            + "SELECT COUNT(B.*) "
            + "FROM BUILDINGS B "
            + "JOIN ZONES Z "
            + "ON Z.ID = B.ID_ZONE "
            + "WHERE B.ID_ZONE = :zoneId";
}
