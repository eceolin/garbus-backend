package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRASH_ICONS")
public class TrashIcons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "COLOR")
    private String color;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_TYPE")
    private TrashType trashType;
}
