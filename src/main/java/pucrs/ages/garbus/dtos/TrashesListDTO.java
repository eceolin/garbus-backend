package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.*;

import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesListDTO {

    private List<TrashesReduceDTO> trashes;

    private List<BuildingsReduceDTO> buildings;

//    private Zones zones;

}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones