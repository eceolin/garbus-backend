package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.TrashesEvents;
import pucrs.ages.garbus.entities.Users;

/**
 * JPA repository to Buildings entities.
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByLoginEquals(String login);
}