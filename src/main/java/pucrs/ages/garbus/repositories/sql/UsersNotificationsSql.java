package pucrs.ages.garbus.repositories.sql;

public class UsersNotificationsSql {
    public final static String findByUserId =
            "SELECT * FROM USERS_NOTIFICATIONS WHERE ID_USER = :userId";
}
