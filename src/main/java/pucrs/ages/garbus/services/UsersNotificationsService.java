package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.NotificationsDisabledUntilWhenDTO;
import pucrs.ages.garbus.entities.NotificationTokens;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.repositories.NotificationTokensRepository;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;
import pucrs.ages.garbus.entities.UsersNotifications;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersNotificationsService {

    private final UsersNotificationsRepository usersNotificationsRepository;
    private final NotificationTokensRepository notificationTokensRepository;
    private final UsersService usersService;


    public Optional<UsersNotifications> findById(Long id) {
        return usersNotificationsRepository.findById(id);
    }

    public void disableNotifications(String login, int seconds) {
        UsersNotifications usersNotifications = usersNotificationsRepository.findByLogin(login);
        LocalDateTime disabledUntil = LocalDateTime.now().plusSeconds(seconds);
        usersNotifications.setDisabledUntil(disabledUntil);
        usersNotificationsRepository.save(usersNotifications);
    }

    public void saveToken(String login, String notificationToken) {
        Optional<NotificationTokens> notificationTokens = Optional.ofNullable(notificationTokensRepository.findByToken(notificationToken));
        if (notificationTokens.isPresent()) {
            return;
        }

        Users user = usersService.findByLogin(login);

        Optional<UsersNotifications> usersNotifications = Optional.ofNullable(usersNotificationsRepository.findByLogin(login));
        if (usersNotifications.isEmpty()) {
            UsersNotifications un = UsersNotifications.builder().users(user).build();
            usersNotificationsRepository.save(un);
        }

        NotificationTokens nt = NotificationTokens.builder().users(user).token(notificationToken).build();
        notificationTokensRepository.save(nt);
    }

    public NotificationsDisabledUntilWhenDTO isDisabledUntilWhen(String login) {
        LocalDateTime disabledUntil = null;
        Optional<UsersNotifications> usersNotifications = Optional.ofNullable(usersNotificationsRepository.findByLogin(login));
        if (usersNotifications.isPresent()) {
            disabledUntil = usersNotifications.get().getDisabledUntil();
        }

        return NotificationsDisabledUntilWhenDTO.builder().disabledUntil(disabledUntil).build();


    }
}
