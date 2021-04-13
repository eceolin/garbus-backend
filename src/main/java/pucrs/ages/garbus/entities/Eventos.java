package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "EVENTOS")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_CRIACAO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCricao;

    @Column(name = "STATUS_PROBLEMA")
    private String statusProblema;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TIPO_EVENTOS")
    private TiposEventos tiposEventos;
}
