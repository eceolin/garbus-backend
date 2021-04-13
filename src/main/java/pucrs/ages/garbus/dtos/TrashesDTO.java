package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.TrashesStatus;
import pucrs.ages.garbus.entities.Buildings;
import pucrs.ages.garbus.entities.TrashType;
import pucrs.ages.garbus.entities.Zones;


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

    private TrashesStatus trashesStatus;

    private TrashType trashType;

    private Buildings buildings;

    private Zones zones;

}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones