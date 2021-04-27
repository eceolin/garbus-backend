package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.TypesEvents;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private long id;

    private String email;

    private String name;

    private String login;

    private boolean blocked;

    private Date registerDate;

    private Profiles profiles;

}