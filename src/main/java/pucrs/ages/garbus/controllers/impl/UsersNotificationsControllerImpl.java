package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.UsersNotificationsController;
import pucrs.ages.garbus.dtos.DisableNotificationsRequest;
import pucrs.ages.garbus.dtos.SaveNotificationTokenRequest;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.services.UsersNotificationsService;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class UsersNotificationsControllerImpl implements UsersNotificationsController {

    @Resource
    private UsersNotificationsService usersNotificationsService;

    @Override
    public ResponseEntity<Object> disableNotifications(DisableNotificationsRequest disableNotificationsRequest, Authentication authentication) {
        try {
            usersNotificationsService.disableNotifications(authentication.getName(), disableNotificationsRequest.getSeconds());
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
    public ResponseEntity<Object> saveToken(SaveNotificationTokenRequest tokenRequest, Authentication authentication) {
        try {
            usersNotificationsService.saveToken(authentication.getName(), tokenRequest.getNotificationToken());
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


