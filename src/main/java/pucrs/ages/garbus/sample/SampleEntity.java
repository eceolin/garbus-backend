package pucrs.ages.garbus.sample;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

//Lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

//Spring JPA
@Entity
@Table(name= "SAMPLE_ENTITIES")
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Builder.Default
    @Column(name = "CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    @NotEmpty
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SAMPLE_NAME")
    private String name;

}
