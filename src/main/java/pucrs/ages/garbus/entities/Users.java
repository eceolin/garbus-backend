package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "USERS")
public class Users {

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

    @Column(name = "MUST_CHANGE_PWD")
    private boolean mustChangePwd;

    @Column(name = "DT_REGISTER")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_PROFILE")
    private Profiles profiles;

}
