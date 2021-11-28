package pucrs.ages.garbus.repositories.sql;

public class UsersNotificationsSql {
    public UsersNotificationsSql() {
    }

    public static final String FIND_BY_USER_ID =
            "SELECT * FROM USERS_NOTIFICATIONS WHERE ID_USER = :userId";

    public static final String FIND_BY_LOGIN =
            "SELECT N.* "
                    + "FROM USERS_NOTIFICATIONS N "
                    + "JOIN USERS U "
                    + "ON U.ID = N.ID_USER "
                    + "WHERE LOWER(U.LOGIN) = LOWER(:login)";
}
