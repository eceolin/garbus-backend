package pucrs.ages.garbus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.entities.Zones;
import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.ZonesRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ZonesServiceTest {

    @InjectMocks
    private ZonesService zonesService;

    @Mock
    private ZonesRepository zonesRepository;

    private ZonesMapper zonesMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        zonesMapper = new ZonesMapper(modelMapper);
        zonesService = new ZonesService(zonesMapper, zonesRepository);
    }

    @Test
    void findAllTest() {
        //Given
        List<Zones> zones = generateZonesEntitiesList();
        List<ZonesDTO> zonesDTOS = generateZoneDtoList();

        //When
        given(zonesRepository.findAll()).willReturn(zones);
        given(zonesRepository.countBuildingsByIdZone(any())).willReturn(1);
        given(zonesRepository.countTrashesByIdZone(any())).willReturn(1);

        List<ZonesDTO> response = zonesService.findAll();

        //Then
        then(response.size()).isEqualTo(1);
        then(response.get(0).getName()).isEqualTo(zonesDTOS.get(0).getName());
        then(response.get(0).getDescription()).isEqualTo(zonesDTOS.get(0).getDescription());
        then(response.get(0).getLatitude()).isEqualTo(zonesDTOS.get(0).getLatitude());
        then(response.get(0).getLongitude()).isEqualTo(zonesDTOS.get(0).getLongitude());
    }

    @Test
    void findByIdTest() {
        //Given
        List<Zones> zones = generateZonesEntitiesList();
        List<ZonesDTO> zonesDTOS = generateZoneDtoList();

        //When
        given(zonesRepository.findById(Long.parseLong("1"))).willReturn(java.util.Optional.ofNullable(zones.get(0)));
        given(zonesRepository.countBuildingsByIdZone(any())).willReturn(1);
        given(zonesRepository.countTrashesByIdZone(any())).willReturn(1);

        ZonesDTO response = zonesService.findById(Long.parseLong("1"));

        //Then
        then(response.getName()).isEqualTo(zonesDTOS.get(0).getName());
        then(response.getDescription()).isEqualTo(zonesDTOS.get(0).getDescription());
        then(response.getLatitude()).isEqualTo(zonesDTOS.get(0).getLatitude());
        then(response.getLongitude()).isEqualTo(zonesDTOS.get(0).getLongitude());
    }

    private List<ZonesDTO> generateZoneDtoList() {
        List<ZonesDTO> zonesDTOS = new ArrayList<>();
        zonesDTOS.add(ZonesDTO.builder()
                .id(1)
                .name("Zone test")
                .longitude(15.5)
                .longitude(15.5)
                .buildingsCount(3)
                .trashesCount(4)
                .build());

        return zonesDTOS;
    }

    private List<Zones> generateZonesEntitiesList() {
        List<Zones> zones = new ArrayList<>();
        zones.add(Zones.builder()
                .id(1)
                .name("Zone test")
                .longitude(15.5)
                .longitude(15.5)
                .build());

        return zones;
    }
}