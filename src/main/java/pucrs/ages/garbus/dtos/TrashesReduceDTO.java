package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TrashesStatus;

import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesReduceDTO {

    private long trashId;

    private double capacity;

    private double occupation;

    private double longitude;

    private double latitude;

    private TrashesStatus trashesStatus;

    private String buildingName;

    private String trashDescription;

    private List<TrashesThresholdDTO> trashesThreshold;
}