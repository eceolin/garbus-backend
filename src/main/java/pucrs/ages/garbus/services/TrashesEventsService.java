package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesEvents;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.enuns.TrashStatusEnum;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.repositories.TrashesEventsRepository;

import javax.annotation.Resource;
import java.util.Calendar;
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

    public void insertErrorOnTrash(Long trashId, Long typeEventId, String login, String others) throws NotFoundException {
        Users users = Optional.ofNullable(usersService.findByLogin(login))
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar usuário logado para o id " + login));
        Trashes trashes = trashesService.findById(trashId)
                .orElseThrow(() -> new NotFoundException("Lixeira não encontrada para o id " + trashId));
        if (trashes.getTrashesStatus().getId() == TrashStatusEnum.MANUTENCAO.getId())
            throw new BadRequestException(String.format("Lixeira com id %s já está inativa", trashId));
        Events events = null;
        if(typeEventId != 0) {
                events = Optional.ofNullable(eventsService.findEventsByTypeEventsId(typeEventId))
                    .orElseThrow(() -> new NotFoundException("Tipo de evento não encontrado para o id " + typeEventId));
        }

        trashesEventsRepository.save(TrashesEvents.builder()
                .events(events)
                .trashes(trashes)
                .users(users)
                .others(others)
                .data(Calendar.getInstance().getTime())
                .build());

    }

}
