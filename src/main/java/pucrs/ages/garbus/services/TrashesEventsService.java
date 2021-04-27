package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesEvents;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.mappers.TrashesMapper;
import pucrs.ages.garbus.repositories.TrashesEventsRepository;
import pucrs.ages.garbus.repositories.TrashesRepository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrashesEventsService {

    @Resource
    private EventsService eventsService;
    @Resource
    private UsersService usersService;
    @Resource
    private TrashesService trashesService;
    private final TrashesEventsRepository trashesEventsRepository;

    public void insertErrorOnTrash(Long trashId, Long typeEventId, String login) throws Exception {
        Optional<Trashes> trashes = Optional.ofNullable(trashesService.findById(trashId));
        Events events = eventsService.findEventsByTypeEventsId(typeEventId);
        Users users = usersService.findByLogin(login);
        if(trashes.isPresent()) {
            trashesEventsRepository.save(TrashesEvents.builder()
                    .events(events)
                    .trashes(trashes.get())
                    .users(users)
                    .data(Calendar.getInstance().getTime())
                    .build());
        }
    }

}
