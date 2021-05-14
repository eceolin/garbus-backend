package pucrs.ages.garbus.repositories.sql;

public class UsersSql {
    public final static String findByLoginSql =
            "SELECT * FROM USERS WHERE USERS.LOGIN = :login";
}
