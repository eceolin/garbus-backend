package pucrs.ages.garbus.dtos;

import lombok.*;
import pucrs.ages.garbus.entities.Events;

import java.util.Date;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesEventsDTO {

    private long id;

    private Events event;

    private String login;

    private double occupation;

    private String others;

    private Date date;

}