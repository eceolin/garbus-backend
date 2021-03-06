package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.excpetion.HttpException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public interface AuthenticationController {
    @PostMapping("/login")
    @ApiOperation("User authentication")
    ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid JwtRequest jwtRequest) throws HttpException;

    @PostMapping("/password-recovery")
    @ApiOperation("Password Recovery")
    ResponseEntity<PasswordRecoveryResponse> recovery(@RequestBody PasswordRecoveryRequest login) throws HttpException;

    @PutMapping("/change-password")
    @ApiOperation("Update password")
    ResponseEntity<String> updatePassword(Authentication authentication, @RequestBody PasswordChangeRequest password);

    @PutMapping("/generate-temp-password/{userId}")
    @ApiOperation("Generate password")
    ResponseEntity<TempPasswordGenerationResponse> generateTempPassword(
            @PathVariable(value = "userId") @Valid @NotEmpty long userId
    );
}
