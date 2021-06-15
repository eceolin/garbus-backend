package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TrashesEventsDTO;
import pucrs.ages.garbus.entities.Events;
import pucrs.ages.garbus.entities.TrashesEvents;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashesEventsMapper {

    private final ModelMapper modelMapper;

    public List<TrashesEventsDTO> mapear(List<TrashesEvents> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesEventsDTO.class))
                .collect(Collectors.toList());
    }

    public TrashesEventsDTO mapear(TrashesEvents source) {
        return modelMapper.map(source, TrashesEventsDTO.class);
    }

}

