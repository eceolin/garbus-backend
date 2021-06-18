package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public interface AuthenticationController {
    @PostMapping("/login")
    @ApiOperation("User authentication")
    ResponseEntity<JwtResponse> authenticate(@RequestBody @Valid JwtRequest jwtRequest) throws Exception;

    @PostMapping("/password-recovery")
    @ApiOperation("Password Recovery")
    ResponseEntity<PasswordRecoveryResponse> recovery(@RequestBody PasswordRecoveryRequest login) throws Exception;

    @PutMapping("/change-password")
    @ApiOperation("Update password")
    ResponseEntity<String> updatePassword(@RequestParam(value = "userId") @Valid @NotEmpty long userId,
                                          @RequestParam(value = "password") @Valid @NotEmpty String password);

    @PutMapping("/generate-temp-password/{userId}")
    @ApiOperation("Generate password")
    ResponseEntity<TempPasswordGenerationResponse> generateTempPassword(@PathVariable(value = "userId") @Valid @NotEmpty long userId);
}
