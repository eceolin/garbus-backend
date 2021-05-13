package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.Utils.JWTUtility;
import pucrs.ages.garbus.dtos.JwtRequest;
import pucrs.ages.garbus.dtos.JwtResponse;
import pucrs.ages.garbus.dtos.PasswordRecoveryResponse;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UsersAuthenticationService {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UsersRepository usersRepository;

    public JwtResponse authenticateUser(JwtRequest jwtRequest) throws Exception {

        Users user = usersRepository.findByLogin(jwtRequest.getLogin());
        if (user == null || !user.getPassword().equals(jwtRequest.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getLogin(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }

        final UserDetails userDetails = new User(jwtRequest.getLogin(), jwtRequest.getPassword(), new ArrayList<>());
        final String token = jwtUtility.generateToken(userDetails, user);

        return new JwtResponse(token);
    }

    public PasswordRecoveryResponse recoveryPassword(String login) {

        return null;
    }
}
