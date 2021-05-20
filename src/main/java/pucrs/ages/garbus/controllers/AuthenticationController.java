package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pucrs.ages.garbus.dtos.*;

import javax.validation.Valid;

public interface AuthenticationController {
    @PostMapping("/login")
    @ApiOperation("User authentication")
    ResponseEntity<JwtResponse> authenticate(@RequestBody @Valid JwtRequest jwtRequest) throws Exception;

    @PostMapping("/password-recovery")
    @ApiOperation("Password Recovery")
    ResponseEntity<PasswordRecoveryResponse> recovery(@RequestBody PasswordRecoveryRequest login) throws Exception;
}
