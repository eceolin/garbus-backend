package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersNotificationsService {

    private final UsersNotificationsRepository usersNotificationsRepository;

    public Optional<UsersNotifications> findById(Long id) {
        return usersNotificationsRepository.findById(id);
    }

    public void disableNotifications(String login) {
        UsersNotifications usersNotifications = usersNotificationsRepository.findByLogin(login);
        usersNotifications.setUserNotificationFlag(false);
        usersNotificationsRepository.save(usersNotifications);
    }
}
