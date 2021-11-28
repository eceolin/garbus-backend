package pucrs.ages.garbus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pucrs.ages.garbus.entities.TrashIcons;
import pucrs.ages.garbus.entities.TrashType;

import java.util.Optional;

public interface TrashIconsRepository extends JpaRepository<TrashIcons, Long> {
    public Optional<TrashIcons> findByTrashTypeAndColor(TrashType trashType, String color);
}
