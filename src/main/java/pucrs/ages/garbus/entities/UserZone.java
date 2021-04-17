package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "USER_ZONE")
public class UserZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_USER")
    private List<Users> users;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_ZONE")
    private List<Zones> zones;

}
