package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/notifications")
public interface UsersNotificationsController {
    @PostMapping("/disable/{userId}")
    @ApiOperation("Update password")
    ResponseEntity<Object> disableNotifications(@PathVariable(value = "userId") @NotNull long userId);

    @PutMapping("/save-token")
    @ApiOperation("Save token")
    ResponseEntity<Object> saveToken(@RequestParam(value = "userId") @NotNull long userId,
                                     @RequestParam(value = "notificationToken") @NotNull String notificationToken);
}
