package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "LIXEIRA")
public class Lixeiras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "MARCA")
    private String marca;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "CAPACIDADE")
    private double capacidade;

    @Column(name = "OCUPACAO")
    private double ocupacao;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_STATUS")
    private LixeiraStatus lixeiraStatus;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TIPO")
    private TipoLixeira tipoLixeira;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_PREDIO")
    private Predios predios;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_ZONA")
    private Zonas zonas;

}
