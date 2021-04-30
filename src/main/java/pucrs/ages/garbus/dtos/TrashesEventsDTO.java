package pucrs.ages.garbus.dtos;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import pucrs.ages.garbus.entities.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;


//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashesEventsDTO {

    private long id;

    private List<Events> events;

    private List<Trashes> trashes;

    private List<Users> users;

    private double occupation;

    private Date data;

}
//    t.id, t.brand, t.description, t.capacity, t.occupation, t.trashesStatus, t.trashType, t.buildings, t.zones