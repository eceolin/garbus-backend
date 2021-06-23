package pucrs.ages.garbus.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.repositories.NotificationTokensRepository;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UsersNotificationsServiceTest {
    @InjectMocks
    private UsersNotificationsService usersNotificationsService;

    @Mock
    private UsersNotificationsRepository usersNotificationsRepository;

    @Mock
    private NotificationTokensRepository notificationTokensRepository;

    @Mock
    private UsersService usersService;

    @BeforeEach
    void setup() {
        usersNotificationsService = new UsersNotificationsService(usersNotificationsRepository, notificationTokensRepository, usersService);
    }

    @Test
    @DisplayName("Reactivate notifications")
    void reactivateNotifications() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        LocalDateTime disabledUntil = LocalDateTime.now();

        // When
        given(usersNotificationsRepository.findByLogin(login)).willReturn(
                Optional.ofNullable(
                        UsersNotifications.builder().users(users.get(0)).disabledUntil(disabledUntil).build()
                )
        );
        usersNotificationsService.reactivate(login);

        // Then
        Optional<UsersNotifications> nr = usersNotificationsRepository.findByLogin(login);
        if (nr.isPresent()) {
            then(nr.get().getDisabledUntil()).isNull();
        } else {
            fail("UsersNotifications not found");
        }
    }

    @Test
    @DisplayName("Try to reactivate notifications but there aren't registered devices")
    void reactivateNotificationsNotFound() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();

        // Then
        assertThatThrownBy(() -> usersNotificationsService.reactivate(login)).isInstanceOf(BadRequestException.class);
    }

    private List<Users> generateUsers() {
        List<Users> users = new ArrayList<>();
        users.add(Users.builder().id(1).name("Test User").login("test").build());
        return users;
    }
}
