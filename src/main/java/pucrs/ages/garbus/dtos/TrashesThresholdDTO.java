package pucrs.ages.garbus.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesThresholdDTO {

    private long id;

    private double maxOcuppation;

    private String color;

}
