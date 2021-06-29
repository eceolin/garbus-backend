package pucrs.ages.garbus.repositories.sql;

public class TrashesThresholdsSql {

    public final static String findAllThresholdsSql =
            "SELECT * FROM TRASHES_THRESHOLD t ORDER BY MAX_OCCUPATION";

    public final static String findThresholdsMaxOccupationByTrashIdSql =
            "SELECT * WHERE LOWER(t.COLOR) = LOWE('RED') and t.ID_TRASH = :trashId";

    public final static String findThresholdsByTrashIdSql =
            "SELECT t.ID, t.MAX_OCCUPATION, t.COLOR, t.ID_TRASH FROM TRASHES_THRESHOLD t WHERE t.ID_TRASH = :trashId ORDER BY MAX_OCCUPATION";
}
