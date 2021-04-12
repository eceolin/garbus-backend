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
@Table(name= "LIXEIRA_EVENTOS")
public class LixeiraEventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_EVENTO")
    private List<Eventos> eventos;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_LIXEIRA")
    private List<Lixeiras> lixeiras;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_USUARIO")
    private List<Usuarios> usuarios;

    @NotEmpty
    @Column(name = "OCUPACAO")
    private double ocupacao;

    @Column(name = "DATA")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

}
