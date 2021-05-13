package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pucrs.ages.garbus.dtos.JwtRequest;
import pucrs.ages.garbus.dtos.JwtResponse;
import pucrs.ages.garbus.dtos.PasswordRecoveryResponse;
import pucrs.ages.garbus.dtos.UsersDTO;

import javax.validation.Valid;

public interface AuthenticationController {

    @PostMapping("/login")
    @ApiOperation("User authentication")
    ResponseEntity<JwtResponse> authenticate(@RequestBody @Valid JwtRequest jwtRequest) throws Exception;

    @PostMapping("/password-recovery")
    @ApiOperation("Password Recovery")
    ResponseEntity<PasswordRecoveryResponse> recovery(@RequestBody String login) throws Exception;
}
