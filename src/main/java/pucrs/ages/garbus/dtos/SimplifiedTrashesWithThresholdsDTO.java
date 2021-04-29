package pucrs.ages.garbus.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedTrashesWithThresholdsDTO {

    private long trashId;

    private double occupation;

    private String buildingName;

    private String trashDescription;

    private List<TrashesThresholdDTO> trashesThreshold;
}
