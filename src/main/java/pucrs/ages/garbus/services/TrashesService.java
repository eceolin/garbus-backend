package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.SimplifiedTrashesWithThresholdsDTO;
import pucrs.ages.garbus.dtos.TrashDetailsDTO;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.mappers.SimplifiedTrashesWithThresholdsMapper;
import pucrs.ages.garbus.mappers.TrashDetailsMapper;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.repositories.BuildingsRepository;
import pucrs.ages.garbus.repositories.TrashesRepository;
import pucrs.ages.garbus.repositories.TrashesThresholdsRepository;
import pucrs.ages.garbus.repositories.ZonesRepository;

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
    private final TrashesMapper trashMapper;
    private final TrashesRepository trashesRepository;
    private final TrashesThresholdsRepository trashesThresholdsRepository;
    private final ZonesRepository zonesRepository;
    private final BuildingsRepository buildingsRepository;
    private final TrashDetailsMapper trashDetailsMapper;
    private final SimplifiedTrashesWithThresholdsMapper simplifiedTrashesWithThresholdsMapper;

    public List<TrashesDTO> findAll() {
        return trashMapper.mapear(trashesRepository.findAll());
    }

    public TrashesDTO findByIdDTO(Long id) {
        return trashMapper.mapear(trashesRepository.findById(id));
    }

    public Optional<Trashes> findById(Long id) {
        return trashesRepository.findById(id);
    }

    public List<TrashesDTO> findAllByZonesId(Long zoneId) {
        return trashMapper.mapear(trashesRepository.findByZonesIdAndBuildingsInZonesId(zoneId));
    }

    public void updateStatus(Trashes trashes) {
        trashesRepository.saveAndFlush(trashes);
    }

    public void insertErrorOnTrash(Long trashId, Long typeEventId, String login) throws Exception {
        trashesEventsService.insertErrorOnTrash(trashId, typeEventId, login);
        Optional<Trashes> trashes = findById(trashId);
        if (!trashes.isEmpty()) {
            trashes.get()
                    .setTrashesStatus(trashesStatusService.findById(ID_STATUS_INATIVO).get());
            updateStatus(trashes.get());
        }
    }

    public List<SimplifiedTrashesWithThresholdsDTO> findAllByBuildingId(Long buildingId) {
        return simplifiedTrashesWithThresholdsMapper.mapToDTO(trashesRepository.findByBuildingId(buildingId), trashesThresholdsRepository.findAllThresholds());
    }

    public TrashDetailsDTO findTrashById(Long trashId) {
        Trashes trash = trashesRepository.findTrashByTrashId(trashId);
        String localDescription;
        if (trash.getZones() != null) {
            localDescription = zonesRepository.findZoneDescriptionByTrashId(trash.getZones().getId());
        } else {
            localDescription = buildingsRepository.findBuildingNameByTrashId(trash.getBuildings().getId());
        }
        return trashDetailsMapper.mapToDTO(trash, localDescription, trashesThresholdsRepository.findThresholdsByTrashId(trashId));
    }
}
