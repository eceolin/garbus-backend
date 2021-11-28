package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pucrs.ages.garbus.entities.TypesEvents;

import java.util.Date;


//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private long id;

    private String description;

    private Date creationDate;

    private char problemStatus;

    private TypesEvents typesEvents;

}