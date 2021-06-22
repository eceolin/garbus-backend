package pucrs.ages.garbus.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pucrs.ages.garbus.dtos.NotificationsDisabledUntilWhenDTO;
import pucrs.ages.garbus.entities.NotificationTokens;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.entities.UsersNotifications;
import pucrs.ages.garbus.repositories.NotificationTokensRepository;
import pucrs.ages.garbus.repositories.UsersNotificationsRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UsersNotificationsServiceTest {
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
    @DisplayName("Salvando token que já existe")
    void saveToken() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        String token = "asdfhjklasdfhjklasdfhjkl";

        // When
        given(notificationTokensRepository.findByToken(token)).willReturn(NotificationTokens.builder().token(token).build());
        usersNotificationsService.saveToken(login, token);

        // Then
        then(notificationTokensRepository.findByToken(token).getToken().equals(token));
    }

    @Test
    void isDisabledUntilWhen() {
        // Given
        List<Users> users = generateUsers();
        String login = users.get(0).getLogin();
        LocalDateTime disabledUntil = LocalDateTime.now().plusSeconds(10);

        // When
        given(usersNotificationsRepository.findByLogin(login)).willReturn(
                UsersNotifications.builder().users(users.get(0)).disabledUntil(disabledUntil).build()
        );
        NotificationsDisabledUntilWhenDTO disabledUntilWhenDto = usersNotificationsService.isDisabledUntilWhen(login);

        // Then
        then(disabledUntilWhenDto.getDisabledUntil().equals(disabledUntil));
    }

    private List<Users> generateUsers() {
        List<Users> users = new ArrayList<>();
        users.add(Users.builder().id(1).name("Test User").login("test").build());
        return users;
    }
}
