package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.dtos.TrashesAndBuildingsOnMapDTO;
import pucrs.ages.garbus.dtos.TrashesReduceDTO;
import pucrs.ages.garbus.entities.Trashes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class TrashesMapper {

    private final ModelMapper modelMapper;

    public TrashesDTO mapear(Optional<Trashes> Trashes) {
        return modelMapper.map(Trashes, TrashesDTO.class);
    }

    public List<TrashesDTO> mapear(List<Trashes> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesDTO.class))
                .collect(Collectors.toList());
    }

    public List<TrashesAndBuildingsOnMapDTO> mapearTrashesList(List<Trashes> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesAndBuildingsOnMapDTO.class))
                .collect(Collectors.toList());
    }

    public List<TrashesReduceDTO> mapearToReduce(List<Trashes> source) {
        return source
                .stream()
                .map(entity -> modelMapper.map(entity, TrashesReduceDTO.class))
                .collect(Collectors.toList());
    }
}

