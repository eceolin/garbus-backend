package pucrs.ages.garbus.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedTrashesWithThresholdsDTO {

    private String buildingName;

    private String trashDescription;

    private List<TrashesThresholdDTO> trashesThreshold;
}
