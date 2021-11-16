package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.AuthenticationController;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.dtos.PasswordRecoveryRequest;
import org.springframework.security.core.Authentication;
import pucrs.ages.garbus.excpetion.HttpException;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.repositories.UsersRepository;
import pucrs.ages.garbus.services.UsersAuthenticationService;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    @Resource
    private UsersAuthenticationService usersAuthenticationService;

    @Override
    public ResponseEntity<LoginResponse> authenticate(JwtRequest jwtRequest) throws HttpException {
        return new ResponseEntity<>(usersAuthenticationService.authenticateUser(jwtRequest), OK);
    }

    @Override
    public ResponseEntity<PasswordRecoveryResponse> recovery(PasswordRecoveryRequest login) throws HttpException {
        try {
            return new ResponseEntity<>(usersAuthenticationService.recoveryPassword(login.getLogin()), OK);
        } catch (NotFoundException e) {
            log.error("Error", e);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updatePassword(Authentication authentication, PasswordChangeRequest password){
        return new ResponseEntity<>(usersAuthenticationService.changePassword(authentication.getName(), password.getPassword()), OK);
    }

    @Override
    public ResponseEntity<TempPasswordGenerationResponse> generateTempPassword(long userId){
        return new ResponseEntity<>(usersAuthenticationService.generateTempPassword(userId), OK);
    }
}
