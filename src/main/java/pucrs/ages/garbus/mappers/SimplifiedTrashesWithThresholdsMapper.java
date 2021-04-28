package pucrs.ages.garbus.mappers;

import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pucrs.ages.garbus.dtos.SimplifiedTrashesWithThresholdsDTO;
import pucrs.ages.garbus.dtos.TrashesThresholdDTO;
import pucrs.ages.garbus.entities.Trashes;
import pucrs.ages.garbus.entities.TrashesThreshold;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Mapper(componentModel = "spring")
public class SimplifiedTrashesWithThresholdsMapper {
    private final TrashesThresholdMapper trashesThresholdMapper;

    public List<SimplifiedTrashesWithThresholdsDTO> mapToDTO(List<Trashes> trashes, List<TrashesThreshold> trashesThresholds) {
        List<SimplifiedTrashesWithThresholdsDTO> simplifiedTrashesWithThresholdsList = new ArrayList<>();
        SimplifiedTrashesWithThresholdsDTO simplifiedTrashesWithThresholds = new SimplifiedTrashesWithThresholdsDTO();
        for (Trashes trash : trashes) {
            List<TrashesThresholdDTO> tempTrashesThresholds = new ArrayList<>();
            getTrashThresholds(trashesThresholds, trash, tempTrashesThresholds);
            simplifiedTrashesWithThresholds = SimplifiedTrashesWithThresholdsDTO.builder()
                    .buildingName(trash.getBuildings().getName())
                    .trashDescription(trash.getDescription())
                    .trashesThreshold(tempTrashesThresholds)
                    .build();
            simplifiedTrashesWithThresholdsList.add(simplifiedTrashesWithThresholds);
        }
        return simplifiedTrashesWithThresholdsList;
    }

    private void getTrashThresholds(List<TrashesThreshold> trashesThresholds, Trashes trash, List<TrashesThresholdDTO> tempTrashesThresholds) {
        for (int i = 0; i < trashesThresholds.size(); i++) {
            if (trashesThresholds.get(i).getTrashes().getId() == trash.getId()) {
                trashesThresholds.get(i).setTrashes(null);
                tempTrashesThresholds.add(trashesThresholdMapper.mapToDTO(trashesThresholds.get(i)));
                trashesThresholds.remove(i);
                i--;
            }
        }
    }
}
