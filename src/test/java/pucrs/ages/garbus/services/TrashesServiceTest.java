package pucrs.ages.garbus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.TrashesAndBuildingsOnMapDTO;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.entities.*;
import pucrs.ages.garbus.mappers.SimplifiedTrashesWithThresholdsMapper;
import pucrs.ages.garbus.mappers.TrashDetailsMapper;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.mappers.TrashesThresholdMapper;
import pucrs.ages.garbus.repositories.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TrashesServiceTest {

    @Mock
    private TrashesRepository trashesRepository;

    @Mock
    private TrashesStatusRepository trashesStatusRepository;

    @Mock
    private TrashesThresholdsRepository trashesThresholdsRepository;

    @Mock
    private ZonesRepository zonesRepository;

    @Mock
    private BuildingsRepository buildingsRepository;

    @Mock
    private TrashesEventsRepository trashesEventsRepository;

    @Mock
    private EventsRepository eventsRepository;

    private TrashesService trashesService;

    @Mock
    private TrashesEventsService trashesEventsService;

    @Mock
    private TrashesStatusService trashesStatusService;


    private ModelMapper modelMapper;
    private TrashesMapper trashMapper;
    private TrashDetailsMapper trashDetailsMapper;
    private TrashesThresholdMapper trashesThresholdMapper;
    private SimplifiedTrashesWithThresholdsMapper simplifiedTrashesWithThresholdsMapper;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        trashMapper = new TrashesMapper(modelMapper);
        trashesThresholdMapper = new TrashesThresholdMapper(modelMapper);
        trashDetailsMapper = new TrashDetailsMapper(trashesThresholdMapper);
        simplifiedTrashesWithThresholdsMapper = new SimplifiedTrashesWithThresholdsMapper(trashesThresholdMapper, trashMapper);
        trashesService = new TrashesService(trashMapper, trashesRepository, trashesStatusRepository, trashesThresholdsRepository,
                zonesRepository, buildingsRepository, trashDetailsMapper, trashesThresholdMapper, simplifiedTrashesWithThresholdsMapper,
                trashesEventsRepository, eventsRepository);
    }

    @Test
    void findAll() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();
        List<TrashesThreshold> trashesThresholds = generateTrashesThresholdsEntitiesList();

        //When
        given(trashesRepository.findAll()).willReturn(trashes);
        given(trashesThresholdsRepository.findAllThresholds()).willReturn(trashesThresholds);

        TrashesAndBuildingsOnMapDTO response = trashesService.findAll();

        //Then
        then(response.getTrashes().size()).isEqualTo(1);
        then(response.getTrashes().get(0).getTrashId()).isEqualTo(2);
        then(response.getTrashes().get(0).getTrashesThreshold().get(0).getColor()).isEqualTo(trashesThresholds.get(0).getColor());
        then(response.getTrashes().get(0).getTrashesThreshold().get(0).getMaxOcuppation()).isEqualTo(trashesThresholds.get(0).getMaxOcuppation());
        then(response.getTrashes().get(0).getCapacity()).isEqualTo(trashes.get(1).getCapacity());
        then(response.getBuildings().size()).isEqualTo(1);
        then(response.getBuildings().get(0).getId()).isEqualTo(1);
        then(response.getBuildings().get(0).getName()).isEqualTo(trashes.get(0).getBuildings().getName());
        then(response.getBuildings().get(0).getTrashesCount()).isEqualTo(1);
    }

    @Test
    void findAllByZonesId() {
        //Given
        List<Trashes> trashes = generateTrashesEntitiesList();

        //When
        given(trashesRepository.findByZonesIdAndBuildingsInZonesId(Long.parseLong("1"))).willReturn(trashes);

        List<TrashesDTO> response = trashesService.findAllByZonesId(Long.parseLong("1"));

        //Then
        then(response.size()).isEqualTo(2);
        then(response.get(0).getTrashId()).isEqualTo(trashes.get(0).getId());
        then(response.get(0).getZone()).isEqualTo(trashes.get(0).getZones());
        then(response.get(1).getTrashId()).isEqualTo(trashes.get(1).getId());
        then(response.get(1).getZone()).isEqualTo(trashes.get(1).getZones());
    }

    private List<Trashes> generateTrashesEntitiesList() {
        List<Trashes> trashes = new ArrayList<>();
        trashes.add(Trashes.builder()
                .id(1)
                .buildings(Buildings.builder()
                        .id(1)
                        .name("Building test")
                        .zones(Zones.builder()
                                .id(1)
                                .name("Zone test")
                                .build())
                        .build())
                .brand("Brand test")
                .trashType(TrashType.builder()
                        .id(2)
                        .name("Dry")
                        .build())
                .trashesStatus(TrashesStatus.builder()
                        .id(4)
                        .name("Ativa")
                        .description("A lixeira está em funcionamento")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .build());
        trashes.add(Trashes.builder()
                .id(2)
                .brand("Brand test 2")
                .trashType(TrashType.builder()
                        .id(1)
                        .name("Organic")
                        .build())
                .zones(Zones.builder()
                        .id(1)
                        .name("Zone test")
                        .build())
                .capacity(50.0)
                .occupation(80.0)
                .build());

        return trashes;
    }

    private List<TrashesThreshold> generateTrashesThresholdsEntitiesList() {
        List<TrashesThreshold> trashesThresholds = new ArrayList<>();
        trashesThresholds.add(TrashesThreshold.builder()
                .id(1)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(16.8)
                .color("GREEN")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(2)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(43.2)
                .color("YELLOW")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(3)
                .trashes(generateTrashesEntitiesList().get(0))
                .maxOcuppation(9999999.99)
                .color("RED")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(1)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(16.8)
                .color("GREEN")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(2)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(43.2)
                .color("YELLOW")
                .build());
        trashesThresholds.add(TrashesThreshold.builder()
                .id(3)
                .trashes(generateTrashesEntitiesList().get(1))
                .maxOcuppation(9999999.99)
                .color("RED")
                .build());

        return trashesThresholds;
    }

}