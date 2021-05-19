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
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersAuthenticationService {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

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
        Users user = Optional.ofNullable(usersRepository.findByLoginEquals(login))
                .orElseThrow(() -> new NotFoundException(new ErrorResponse(String.format("Usuário com Login %s não encontrado.", login))));
        PasswordRecoveryResponse passwordRecoveryResponse = new PasswordRecoveryResponse();
        if (! Objects.isNull(user) && !Objects.isNull(user.getEmail()) && !user.getEmail().isBlank()) {
            passwordRecoveryResponse.setHasEmail(true);
            passwordRecoveryResponse.setEmailSent(emailService.sendTo(user.getEmail(),"Recuperação Senha", "Favor efetuar reset de senha utilizando link abaixo."));
            return passwordRecoveryResponse;
        }
        passwordRecoveryResponse.setHasEmail(false);
        passwordRecoveryResponse.setEmailSent(false);
        return passwordRecoveryResponse;
    }
}
