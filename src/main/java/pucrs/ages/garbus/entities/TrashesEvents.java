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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "TRASHES_EVENTS")
public class TrashesEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_EVENT")
    private List<Events> events;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_TRASH")
    private List<Trashes> trashes;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_USER")
    private List<Users> users;

    @NotEmpty
    @Column(name = "OCCUPATION")
    private double occupation;

    @Column(name = "DATA")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

}
