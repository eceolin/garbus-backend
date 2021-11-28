package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.entities.TrashIcons;
import pucrs.ages.garbus.entities.TrashType;
import pucrs.ages.garbus.repositories.TrashIconsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashIconsService {
    private final TrashIconsRepository trashIconsRepository;

    public List<TrashIcons> findAll() { return trashIconsRepository.findAll(); }

    public Optional<TrashIcons> findById(Long id) { return trashIconsRepository.findById(id); }

    public Optional<TrashIcons> findByTrashTypeAndColor(TrashType trashType, String color) {
        return trashIconsRepository.findByTrashTypeAndColor(trashType, color);
    };
}
