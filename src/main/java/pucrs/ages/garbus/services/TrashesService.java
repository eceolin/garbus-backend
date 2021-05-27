package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.enuns.TrashStatusEnum;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.mappers.SimplifiedTrashesWithThresholdsMapper;
import pucrs.ages.garbus.mappers.TrashDetailsMapper;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.repositories.BuildingsRepository;
import pucrs.ages.garbus.repositories.TrashesRepository;
import pucrs.ages.garbus.repositories.TrashesThresholdsRepository;
import pucrs.ages.garbus.repositories.ZonesRepository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashesService {

    @Resource
    private TrashesEventsService trashesEventsService;
    @Resource
    private TrashesStatusService trashesStatusService;
    private final TrashesMapper trashMapper;
    private final TrashesRepository trashesRepository;
    private final TrashesThresholdsRepository trashesThresholdsRepository;
    private final ZonesRepository zonesRepository;
    private final BuildingsRepository buildingsRepository;
    private final TrashDetailsMapper trashDetailsMapper;
    private final SimplifiedTrashesWithThresholdsMapper simplifiedTrashesWithThresholdsMapper;


    public TrashesAndBuildingsOnMapDTO findAll() {
        return trashesInsideBuildingsAndZones(trashesRepository.findAll());
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

    public void insertErrorInTrash(ErrorRequest errorRequest) {
        validateInput(errorRequest);
        trashesEventsService.insertErrorOnTrash(errorRequest.getTrashId(), errorRequest.getTypeEventId(), errorRequest.getLogin(), errorRequest.getOthers());
        Optional<Trashes> trashes = findById(errorRequest.getTrashId());
        if (trashes.isPresent()) {
            trashes.get()
                    .setTrashesStatus(trashesStatusService.findById(TrashStatusEnum.MANUTENCAO.getId()).get());
            updateStatus(trashes.get());
        }

    }

    private void validateInput(ErrorRequest errorRequest) {
        if(Objects.isNull(errorRequest.getTypeEventId()) && Objects.isNull(errorRequest.getOthers()) ||
           (!Objects.isNull(errorRequest.getTypeEventId()) && !Objects.isNull(errorRequest.getOthers()))) {
            throw new BadRequestException(new ErrorResponse("Deve ser informado ou tipo do evento, ou texto com descrição do problema 'others', exclusivamente"));
        }
    }

    public List<TrashesReduceDTO> findAllByBuildingId(Long buildingId) {
        return simplifiedTrashesWithThresholdsMapper.mapToDTO(trashesRepository.findByBuildingId(buildingId), trashesThresholdsRepository.findAllThresholds());
    }

    public Long getTrashesCountByBuilding(Long buildingId) {
        return (long) trashesRepository.findByBuildingId(buildingId).size();
    }

    public TrashDetailsDTO findTrashById(Long trashId) {
        Trashes trash = trashesRepository.findByTrashId(trashId);
        String localDescription;
        if (trash.getZones() != null) {
            localDescription = zonesRepository.findZoneDescriptionByTrashId(trash.getZones().getId());
        } else {
            localDescription = buildingsRepository.findBuildingNameByTrashId(trash.getBuildings().getId());
        }
        return trashDetailsMapper.mapToDTO(trash, localDescription, trashesThresholdsRepository.findThresholdsByTrashId(trashId));
    }

    public TrashesAndBuildingsOnMapDTO findAllByStatusId(Long statusId) {
        return trashesInsideBuildingsAndZones(trashesRepository.findByStatusId(statusId));
    }

    private Map<Buildings, Long> countAndTrashesInBuildings(List<Trashes> trashesList) {
        return trashesList.stream()
                .filter(building -> building.getBuildings() != null)
                .collect(Collectors.groupingBy(b -> b.getBuildings(),
                        Collectors.counting()));
    }

    private List<BuildingsReduceDTO> buildingsReduceDTOS(Map<Buildings, Long> map) {
        List<BuildingsReduceDTO> buildingsReduceDTOS = new ArrayList<>();
        map.entrySet().stream()
                .forEach(b -> buildingsReduceDTOS.add(
                        BuildingsReduceDTO.builder()
                                .id(b.getKey().getId())
                                .name(b.getKey().getName())
                                .longitude(b.getKey().getLongitude())
                                .latitude(b.getKey().getLatitude())
                                .trashesCount(b.getValue())
                                .build()
                ));
        return buildingsReduceDTOS;
    }

    private List<TrashesReduceDTO> trashesOutBuildings(List<Trashes> trashesList) {
        trashesList = trashesList.stream()
                .filter(building -> building.getBuildings() == null)
                .collect(Collectors.toList());
        return simplifiedTrashesWithThresholdsMapper.mapToDTO(trashesList, trashesThresholdsRepository.findAllThresholds());
    }

    private TrashesAndBuildingsOnMapDTO trashesInsideBuildingsAndZones(List<Trashes> trashesList) {
        return TrashesAndBuildingsOnMapDTO.builder()
                .trashes(trashesOutBuildings(trashesList))
                .buildings(buildingsReduceDTOS(countAndTrashesInBuildings(trashesList)))
                .build();
    }

    public List<TrashesDTO> findListOfTrashes() {
        List<Trashes> trashes = trashesRepository.findAll();
        return simplifiedTrashesWithThresholdsMapper.mapToTrashesDTOWithThresholds(trashes, trashesThresholdsRepository.findAllThresholds());
    }

    public TrashesDTO save(final TrashesDTO trashesDTO)  throws ParseException {
        Trashes trashes = trashMapper.mapearToEntity(trashesDTO);
        return trashMapper.mapear(trashesRepository.saveAndFlush(trashes));
    }

    @Transactional
    public TrashesDTO deleteTrashById(Long trashId) {
        trashesEventsService.deleteByTrashId(trashId);
        trashesThresholdsRepository.deleteTrashesThresholdsByTrashesId(trashId);
        Trashes trash = trashesRepository.findByTrashId(trashId);
        trashesRepository.delete(trash);
        return trashMapper.mapear(trash);
    }
}
