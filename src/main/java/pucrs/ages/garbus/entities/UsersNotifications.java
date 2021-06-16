package pucrs.ages.garbus.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_NOTIFICATIONS")
public class UsersNotifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Autowired
    @OneToOne
    @JoinColumn(name = "ID_USER")
    private Users users;

    @Column(name = "NOTIFICATION_TOKEN")
    private String notificationToken;

    @Column(name = "USER_NOTIFICATION_FLAG")
    private boolean userNotificationFlag;

    @Column(name = "DISABLE_TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disableTime;
}
