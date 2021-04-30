package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.TrashesThreshold;
import pucrs.ages.garbus.entities.Zones;

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

    private String buildingName;

    private String trashDescription;

    private List<TrashesThresholdDTO> trashesThreshold;
}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones