package pucrs.ages.garbus.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "USUARIOS")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "EMAIL")
    private String email;

    @NotEmpty
    @Column(name = "NOME")
    private String nome;

    @NotEmpty
    @Column(name = "LOGIN")
    private String login;

    @NotEmpty
    @Column(name = "SENHA")
    private String senha;

    @NotEmpty
    @Column(name = "BLOQUEADO")
    private boolean bloqueado;

    @Column(name = "DT_CADASTRO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataCadastro;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_PERFIL")
    private Perfis perfis;

//    @Setter
//    public void setSenha(String senha) {
//        this.senha = passwordEncoder().encode(senha);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
