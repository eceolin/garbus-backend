package pucrs.ages.garbus.controllers.impl;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.AuthenticationController;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.repositories.UsersRepository;
import pucrs.ages.garbus.services.UsersAuthenticationService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    @Resource
    private UsersAuthenticationService usersAuthenticationService;

    private final UsersRepository usersRepository;

    @Override
    public ResponseEntity<JwtResponse> authenticate(JwtRequest jwtRequest) throws Exception {
        return new ResponseEntity<>(usersAuthenticationService.authenticateUser(jwtRequest), OK);
    }

    @Override
    public ResponseEntity recovery(PasswordRecoveryRequest login) throws Exception {
        try {
            return new ResponseEntity<>(usersAuthenticationService.recoveryPassword(login.getLogin()), OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> updatePassword(long userId, String password){
        return new ResponseEntity<>(usersAuthenticationService.changePassword(userId, password), OK);
    }
}
