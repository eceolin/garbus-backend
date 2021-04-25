package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.mappers.EventsMapper;
import pucrs.ages.garbus.mappers.TypesEventsMapper;
import pucrs.ages.garbus.repositories.EventsRepository;
import pucrs.ages.garbus.repositories.TypesEventsRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TypesEventsService {

    private final TypesEventsMapper typesEventsMapper;
    private final EventsRepository eventsRepository;
    private final TypesEventsRepository typesEventsRepository;

    @Value("${events.problem_status.error}")
    private char STATUS_ERROR;

    public List<TypesEventsDTO> findAll() {
        return typesEventsMapper.mapearTypeEvents(typesEventsRepository.findAll());
    }

    public List<TypesEventsDTO> findAllErrorTypeEvent() {
        return typesEventsMapper.mapearEventsToTypeEvents(eventsRepository.findEventsByProblemStatusEquals(STATUS_ERROR));
    }
}
