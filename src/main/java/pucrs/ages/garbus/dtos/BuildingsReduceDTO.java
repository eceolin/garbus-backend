package pucrs.ages.garbus.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingsReduceDTO {

    private long id;

    private String name;

    private double longitude;

    private double latitude;

    private Long trashesCount;

}
