package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

@RequestMapping("/notifications")
public interface UsersNotificationsController {
    @PostMapping("/disable/{userId}")
    @ApiOperation("Update password")
    ResponseEntity<Object> disableNotifications(@PathVariable(value = "userId") @NotNull long userId);
}
