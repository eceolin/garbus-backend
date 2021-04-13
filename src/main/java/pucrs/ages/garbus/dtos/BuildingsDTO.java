package pucrs.ages.garbus.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingsDTO {

    private long id;

    private String name;

    private pucrs.ages.garbus.entities.Zones zones;

}
