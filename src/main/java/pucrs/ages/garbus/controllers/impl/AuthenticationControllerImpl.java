package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.AuthenticationController;
import pucrs.ages.garbus.dtos.JwtRequest;
import pucrs.ages.garbus.dtos.JwtResponse;
import pucrs.ages.garbus.dtos.PasswordRecoveryResponse;
import pucrs.ages.garbus.services.UsersAuthenticationService;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    @Resource
    private UsersAuthenticationService usersAuthenticationService;

    @Override
    public ResponseEntity<JwtResponse> authenticate(JwtRequest jwtRequest) throws Exception {
        return new ResponseEntity<>(usersAuthenticationService.authenticateUser(jwtRequest), OK);
    }

    @Override
    public ResponseEntity<PasswordRecoveryResponse> recovery(String login) throws Exception {
        return new ResponseEntity<>(usersAuthenticationService.recoveryPassword(login), OK);
    }
}
