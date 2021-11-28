package pucrs.ages.garbus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.TypesEvents;
import pucrs.ages.garbus.mappers.EventsMapper;
import pucrs.ages.garbus.repositories.EventsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EventsServiceTest {

    @InjectMocks
    private EventsService eventsService;

    @MockBean
    private TypesEventsService typesEventsService;

    @Mock
    private EventsRepository eventsRepository;

    private EventsMapper eventsMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        eventsMapper = new EventsMapper(modelMapper);
        eventsService = new EventsService(typesEventsService, eventsMapper, eventsRepository);
    }

    @Test
    void findAllTest() {
        //Given
        List<EventsDTO> zones = generateEventsDtoList();

        //When
        given(eventsRepository.findAll()).willReturn(generateEventsList());

        List<EventsDTO> response = eventsService.findAll();

        //Then
        then(response.size()).isEqualTo(1);
        then(response.get(0).getId()).isEqualTo(zones.get(0).getId());
        then(response.get(0).getDescription()).isEqualTo(zones.get(0).getDescription());
        then(response.get(0).getTypesEvents()).isEqualTo(zones.get(0).getTypesEvents());
        then(response.get(0).getProblemStatus()).isEqualTo(zones.get(0).getProblemStatus());
    }

    @Test
    void findErrorEventByIdTest() {
        //Given
        Events events = generateEventsList().get(0);

        //When
        given(eventsRepository.findEventsByProblemStatusEqualsAndTypesEventsId('c', Long.parseLong("1"))).willReturn(generateEventsList().get(0));
        given(typesEventsService.statusError()).willReturn('c');

        Events response = eventsService.findErrorEventById(Long.parseLong("1"));

        //Then
        then(response.getId()).isEqualTo(events.getId());
        then(response.getDescription()).isEqualTo(events.getDescription());
        then(response.getTypesEvents()).isEqualTo(events.getTypesEvents());
        then(response.getProblemStatus()).isEqualTo(events.getProblemStatus());
    }

    @Test
    void findAllErrorTypeEventTest() {
        //Given
        List<TypesEventsDTO> typesEventsDTOS = generateTypesEventsDTOList();

        //When
        given(typesEventsService.findAllErrorTypeEvent()).willReturn(generateTypesEventsDTOList());

        List<TypesEventsDTO> response = eventsService.findAllErrorTypeEvent();

        //Then
        then(response.size()).isEqualTo(1);
        then(response.get(0).getId()).isEqualTo(typesEventsDTOS.get(0).getId());
        then(response.get(0).getDescription()).isEqualTo(typesEventsDTOS.get(0).getDescription());
        then(response.get(0).getName()).isEqualTo(typesEventsDTOS.get(0).getName());
    }

    private List<EventsDTO> generateEventsDtoList() {
        List<EventsDTO> eventsDTOS = new ArrayList<>();
        eventsDTOS.add(EventsDTO.builder()
                .id(Long.parseLong("1"))
                .description("Test events DTO description")
                .problemStatus('c')
                .typesEvents(TypesEvents.builder()
                        .id(Long.parseLong("1"))
                        .name("Test types events name")
                        .description("Test types events descrption")
                        .build())
                .build());

        return eventsDTOS;
    }

    private List<Events> generateEventsList() {
        List<Events> eventsDTOS = new ArrayList<>();
        eventsDTOS.add(Events.builder()
                .id(Long.parseLong("1"))
                .description("Test events DTO description")
                .problemStatus('c')
                .typesEvents(TypesEvents.builder()
                        .id(Long.parseLong("1"))
                        .name("Test types events name")
                        .description("Test types events descrption")
                        .build())
                .build());

        return eventsDTOS;
    }

    private List<TypesEventsDTO> generateTypesEventsDTOList() {
        List<TypesEventsDTO> typesEventsDTOS = new ArrayList<>();
        typesEventsDTOS.add(TypesEventsDTO.builder()
                .id(1)
                .name("Test types events name")
                .description("Test types events description")
                .build());

        return typesEventsDTOS;
    }
}