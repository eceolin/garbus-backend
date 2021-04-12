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
@Table(name= "USUARIO_ZONA")
public class UsuarioZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_USUARIO")
    private List<Usuarios> usuarios;

    @Autowired
    @OneToMany
    @JoinColumn(name = "ID_ZONA")
    private List<Zonas> zonas;

}
