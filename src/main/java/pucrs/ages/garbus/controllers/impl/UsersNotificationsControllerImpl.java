package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.UsersNotificationsController;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.services.UsersNotificationsService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class UsersNotificationsControllerImpl implements UsersNotificationsController {

    @Resource
    private UsersNotificationsService usersNotificationsService;

    @Override
    public ResponseEntity<Object> disableNotifications(long userId) {
        try {
            usersNotificationsService.disableNotifications(userId);
            return new ResponseEntity<>("Notificações desativadas", CREATED);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> saveToken(long userId, String notificationToken) {
        try {
            usersNotificationsService.saveToken(userId, notificationToken);
            return new ResponseEntity<>("Token salvo", CREATED);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }
}


