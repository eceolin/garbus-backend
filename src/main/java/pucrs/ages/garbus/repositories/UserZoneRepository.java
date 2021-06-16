package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import pucrs.ages.garbus.entities.UserZone;

/**
 * JPA repository to Buildings entities.
 */
@Repository
public interface UserZoneRepository extends JpaRepository<UserZone, Long> {

    UserZone findUserZoneByUsersId(Long idUsers);

    @Modifying
    void deleteByUsersId(Long idUsers);
}