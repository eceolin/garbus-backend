package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pucrs.ages.garbus.dtos.DisableNotificationsRequest;
import pucrs.ages.garbus.dtos.SaveNotificationTokenRequest;

@RequestMapping("/notifications")
public interface UsersNotificationsController {
    @PostMapping("/disable")
    @ApiOperation("Disable notifications temporarily")
    ResponseEntity<Object> disableNotifications(@RequestBody DisableNotificationsRequest disableNotificationsRequest, Authentication authentication);

    @PutMapping("/save-token")
    @ApiOperation("Save token")
    ResponseEntity<Object> saveToken(@RequestBody SaveNotificationTokenRequest tokenRequest, Authentication authentication);
}
