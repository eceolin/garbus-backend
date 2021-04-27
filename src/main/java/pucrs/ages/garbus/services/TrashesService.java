package pucrs.ages.garbus.services;

import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.repositories.TrashesRepository;
import pucrs.ages.garbus.mappers.TrashesMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.TrashesDTO;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrashesService {

    @Resource
    private TrashesEventsService trashesEventsService;
    private final TrashesMapper maptools;
    private final TrashesRepository repository;

    public List<TrashesDTO> findAll() {
        return maptools.mapear(repository.findAll());
    }

    public TrashesDTO findByIdDTO(Long id) {
        return maptools.mapear(repository.findById(id));
    }

    public Trashes findById(Long id) {
        return maptools.mapearDTO(findByIdDTO(id));
    }

    public List<TrashesDTO> findAllByZonesId(Long zoneId) {
        return maptools.mapear(repository.findByZonesIdAndBuildingsInZonesId(zoneId));
    }

    public void insertErrorOnTrash(Long trashId, Long typeEventId, String login) throws Exception {
        trashesEventsService.insertErrorOnTrash(trashId, typeEventId, login);
    }

}
