package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.entities.Trashes;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashesMapper {

    private final ModelMapper modelMapper;


    public Trashes mapearDTO(TrashesDTO TrashesDTO) throws ParseException {
        return modelMapper.map(TrashesDTO, Trashes.class);
    }


    public List<Trashes> mapearDTO(List<TrashesDTO> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, Trashes.class))
                .collect(Collectors.toList());
    }


    public TrashesDTO mapear(Trashes Trashes) {
        return modelMapper.map(Trashes, TrashesDTO.class);
    }
    public List<TrashesDTO> mapear(List<Trashes> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesDTO.class))
                .collect(Collectors.toList());
    }
}

