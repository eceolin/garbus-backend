package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.repositories.sql.UsersSql;

import java.util.List;

/**
 * JPA repository to Buildings entities.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByLoginEquals(String login);

    @Query(
            value = UsersSql.FIND_BY_LOGIN_SQL,
            nativeQuery = true
    )
    Users findByLogin(@Param("login") String login);


    @Query(
            value = UsersSql.FIND_BY_ZONE_SQL,
            nativeQuery = true
    )
    List<Users> findByZoneId(@Param("zone") long zoneId);
}