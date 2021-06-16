package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    private String email;

    private String name;

    private String login;

    private Date registerDate;

    private Long idProfile;

    private String password;

    private ZonesDTO zone;
}
