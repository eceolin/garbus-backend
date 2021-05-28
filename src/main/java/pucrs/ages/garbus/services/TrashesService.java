package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.entities.TrashesThreshold;
import pucrs.ages.garbus.enuns.TrashStatusEnum;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.mappers.SimplifiedTrashesWithThresholdsMapper;
import pucrs.ages.garbus.mappers.TrashDetailsMapper;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.mappers.TrashesThresholdMapper;
import pucrs.ages.garbus.repositories.*;

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
    private final TrashesStatusRepository trashesStatusRepository;
    private final TrashesThresholdsRepository trashesThresholdsRepository;
    private final ZonesRepository zonesRepository;
    private final BuildingsRepository buildingsRepository;
    private final TrashDetailsMapper trashDetailsMapper;
    private final TrashesThresholdMapper trashesThresholdMapper;
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

    public void insertErrorInTrash(TrashProblemReportDTO trashProblemReport, String login) {
        validateInput(trashProblemReport);

        long trashId = trashProblemReport.getTrashId();
        Trashes trashes = findById(trashId)
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse(
                                String.format("Lixeira com id %s não encontrada", trashId)
                        )
                ));
        TrashesStatus status = trashesStatusService.findById(TrashStatusEnum.MANUTENCAO.getId())
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Status de manutenção não encontrado")
                ));

        if (trashes.getTrashesStatus().getId() == TrashStatusEnum.MANUTENCAO.getId())
            throw new BadRequestException(
                    new ErrorResponse(String.format("A lixeira com id %s já está em manutenção", trashId))
            );

        trashesEventsService.insertTrashProblemReport(
                trashId,
                trashProblemReport.getTypeEventId(),
                trashProblemReport.getOthers(),
                login
        );
        trashes.setTrashesStatus(status);
        updateStatus(trashes);
    }

    public void reactivate(TrashReactivateDTO trashReactivateDTO, String login) throws NotFoundException {
        long trashId = trashReactivateDTO.getTrashId();
        Trashes trashes = this.findById(trashId)
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse(String.format("Lixeira com id %s não encontrada", trashId))
                ));
        TrashesStatus status = trashesStatusService.findById(TrashStatusEnum.ATIVA.getId())
                .orElseThrow(() -> new NotFoundException(
                        new ErrorResponse("Status de reativação não encontrado")
                ));

        if (trashes.getTrashesStatus().getId() == TrashStatusEnum.ATIVA.getId())
            throw new BadRequestException(
                    new ErrorResponse(
                            String.format("A lixeira com id %s já está ativa", trashId)
                    )
            );

        trashesEventsService.insertTrashReactivation(trashId, login);
        trashes.setTrashesStatus(status);
        updateStatus(trashes);
    }

    private void validateInput(TrashProblemReportDTO trashProblemReport) {
        if (Objects.isNull(trashProblemReport.getTypeEventId()) && Objects.isNull(trashProblemReport.getOthers()) ||
                (!Objects.isNull(trashProblemReport.getTypeEventId()) && !Objects.isNull(trashProblemReport.getOthers()))) {
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
        trashes = trashesRepository.saveAndFlush(trashes);
        trashesDTO.setTrashId(trashes.getId());
        saveThreshold(trashesDTO);
        return trashMapper.mapear(trashes);
    }

    @Transactional
    public TrashesDTO deleteTrashById(Long trashId) {
        validateTrash(trashId);
        trashesEventsService.deleteByTrashId(trashId);
        trashesThresholdsRepository.deleteTrashesThresholdsByTrashesId(trashId);
        Trashes trash = trashesRepository.findByTrashId(trashId);
        trashesRepository.delete(trash);
        return trashMapper.mapear(trash);
    }

    @Transactional
    public TrashesDTO updateTrashById(Long trashId, TrashesDTO trashesDTO) throws ParseException {
        validateTrash(trashId);

        trashesDTO.setTrashId(trashId);

        trashesThresholdsRepository.deleteTrashesThresholdsByTrashesId(trashId);

        return save(trashesDTO);
    }


    public void validateTrash(Long trashId) {
        findById(trashId)
                .orElseThrow(() -> new NotFoundException(new ErrorResponse("Lixeira não encontrada para o id " + trashId)));
    }


    private void saveThreshold (TrashesDTO trashesDTO) {
        trashesThresholdsRepository.saveAll(
                trashesThresholdMapper.mapToEntity(
                        trashMapper.mapearToEntity(trashesDTO),
                        trashesDTO.getTrashesThreshold()
                )
        );
    }
}
