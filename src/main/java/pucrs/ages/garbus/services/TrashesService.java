package pucrs.ages.garbus.services;

import pucrs.ages.garbus.repositories.TrashesRepository;
import pucrs.ages.garbus.mappers.TrashesMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.TrashesDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrashesService {

    private final TrashesMapper maptools;
    private final TrashesRepository repository;

    public List<TrashesDTO> findAll() {
        return maptools.mapear(repository.findAll());
    }

    public List<TrashesDTO> findAllByZonesId(Long zoneId) {
        return maptools.mapear(repository.findByZonesIdAndBuildingsInZonesId(zoneId));
    }

    public List<TrashesDTO> findAllByBuildingId(Long buildingId) {
        return maptools.mapear(repository.findByBuildingId(buildingId));
    }


    public List<TrashesDTO> findDetailsByTrashId(Long trashId) {
        return maptools.mapear(repository.findDetailsByTrashId(trashId));
    }
}
