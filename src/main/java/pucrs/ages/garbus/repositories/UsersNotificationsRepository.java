package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.repositories.sql.UsersNotificationsSql;

/**
 * JPA repository to UserNotifications entities.
 */
@Repository
public interface UsersNotificationsRepository extends JpaRepository<UsersNotifications, Long> {
    @Query(
            value = UsersNotificationsSql.findByUserId,
            nativeQuery = true
    )
    UsersNotifications findByUserId(@Param("userId") Long userId);
}
