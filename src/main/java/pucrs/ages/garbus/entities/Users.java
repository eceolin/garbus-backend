package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "USERS")
public class Users {

    public Users (UsersRequestDTO usersRequestDTO, Profiles profiles) {
        this.email = usersRequestDTO.getEmail();
        this.name = usersRequestDTO.getName();
        this.login = usersRequestDTO.getLogin();
        this.password = usersRequestDTO.getPassword();
        this.registerDate = usersRequestDTO.getRegisterDate();
        this.profiles = profiles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "EMAIL")
    private String email;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Column(name = "LOGIN")
    private String login;

    @NotEmpty
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BLOCKED")
    private boolean blocked;

    @Column(name = "DT_REGISTER")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_PROFILE")
    private Profiles profiles;

    public void updateBy(UsersRequestDTO usersRequestDTO) {
        if (Objects.nonNull(usersRequestDTO.getEmail())) {
            this.email = usersRequestDTO.getEmail();
        }
        if (Objects.nonNull(usersRequestDTO.getName())) {
            this.name = usersRequestDTO.getName();
        }
        if (Objects.nonNull(usersRequestDTO.getPassword())) {
            this.password = usersRequestDTO.getPassword();
        }
        if (Objects.nonNull(usersRequestDTO.getLogin())) {
            this.login = usersRequestDTO.getLogin();
        }
    }
}
