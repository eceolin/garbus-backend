package pucrs.ages.garbus.repositories.sql;

public class TrashesThresholdsSql {
    public TrashesThresholdsSql() {
    }

    public static final String FIND_ALL_THRESHOLDS_SQL =
            "SELECT * FROM TRASHES_THRESHOLD t ORDER BY MAX_OCCUPATION";

    public static final String FIND_THRESHOLDS_MAX_OCCUPATION_BY_TRASH_ID_SQL =
            "SELECT * FROM TRASHES_THRESHOLD t WHERE LOWER(t.COLOR) = LOWER('YELLOW') and t.ID_TRASH = :trashId";

    public static final String FIND_THRESHOLDS_BY_TRASH_ID_SQL =
            "SELECT t.ID, t.MAX_OCCUPATION, t.COLOR, t.ID_TRASH FROM TRASHES_THRESHOLD t WHERE t.ID_TRASH = :trashId ORDER BY MAX_OCCUPATION";
}
