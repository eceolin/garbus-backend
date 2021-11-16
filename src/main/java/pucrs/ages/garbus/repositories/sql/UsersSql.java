package pucrs.ages.garbus.repositories.sql;

public class UsersSql {
    public UsersSql() {
    }

    public static final String FIND_BY_LOGIN_SQL =
            "SELECT * FROM USERS WHERE LOWER(USERS.LOGIN) = LOWER(:login)";

    public static final String FIND_BY_ZONE_SQL =
            "SELECT * FROM USERS WHERE ID_ZONE = :zone";
}
