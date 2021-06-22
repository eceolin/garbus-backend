package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.DisableNotificationsRequest;
import pucrs.ages.garbus.dtos.SaveNotificationTokenRequest;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/notifications")
public interface UsersNotificationsController {
    @PostMapping("/disable")
    @ApiOperation("Disable notifications temporarily")
    ResponseEntity<Object> disableNotifications(@RequestBody DisableNotificationsRequest disableNotificationsRequest, Authentication authentication);

    @PutMapping("/save-token")
    @ApiOperation("Save token")
    ResponseEntity<Object> saveToken(@RequestBody SaveNotificationTokenRequest tokenRequest, Authentication authentication);

    @GetMapping("/is-disabled")
    @ApiOperation("Check if and until when notifications are disabled for the user")
    ResponseEntity<Object> isDisabledUntilWhen(@ApiIgnore Authentication authentication);
}
