package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.mappers.EventsMapper;
import pucrs.ages.garbus.repositories.EventsRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsService {

    @Resource
    private TypesEventsService typesEventsService;
    private final EventsMapper eventsMapper;
    private final EventsRepository eventsRepository;

    public List<EventsDTO> findAll() {
        return eventsMapper.mapear(eventsRepository.findAll());
    }

    public List<TypesEventsDTO> findAllErrorTypeEvent() {
        return typesEventsService.findAllErrorTypeEvent();
    }

    public Events findEventsByTypeEventsId(Long id) {
        return eventsRepository.findEventsByProblemStatusEqualsAndTypesEventsId(typesEventsService.statusError(), id);
    }

    public Events teste(Long id) {
        return eventsRepository.findEventsByTypesEventsId(id);
    }

}
