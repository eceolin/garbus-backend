package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.TrashType;
import pucrs.ages.garbus.entities.Zones;

import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesDTO {

    private long id;

    private String brand;

    private String description;

    private double capacity;

    private double occupation;

    private double longitude;

    private double latitude;

    private TrashesStatus trashesStatus;

    private TrashType trashType;

    private List<TrashesThresholdDTO> trashesThreshold;

    private Buildings buildings;

    private Zones zones;

}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones