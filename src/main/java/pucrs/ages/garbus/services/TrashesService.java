package pucrs.ages.garbus.services;

import org.springframework.beans.factory.annotation.Value;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.repositories.TrashesRepository;
import pucrs.ages.garbus.mappers.TrashesMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.TrashesDTO;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashesService {

    @Resource
    private TrashesEventsService trashesEventsService;
    @Resource
    private TrashesStatusService trashesStatusService;
    @Value("${id-status.inativo}")
    private Long ID_STATUS_INATIVO;
    private final TrashesMapper maptools;
    private final TrashesRepository repository;

    public List<TrashesDTO> findAll() {
        return maptools.mapear(repository.findAll());
    }

    public TrashesDTO findByIdDTO(Long id) {
        return maptools.mapear(repository.findById(id));
    }

    public Optional<Trashes> findById(Long id) {
        return repository.findById(id);
    }

    public List<TrashesDTO> findAllByZonesId(Long zoneId) {
        return maptools.mapear(repository.findByZonesIdAndBuildingsInZonesId(zoneId));
    }

    public void updateStatus(Trashes trashes) {
        repository.saveAndFlush(trashes);
    }

    public void insertErrorOnTrash(Long trashId, Long typeEventId, String login) throws Exception {
        trashesEventsService.insertErrorOnTrash(trashId, typeEventId, login);
        Optional<Trashes> trashes = findById(trashId);
        if(!trashes.isEmpty()) {
            trashes.get()
                    .setTrashesStatus(trashesStatusService.findById(ID_STATUS_INATIVO).get());
            updateStatus(trashes.get());
        }
    }

}
