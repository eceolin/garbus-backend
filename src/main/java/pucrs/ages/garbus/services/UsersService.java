package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.mappers.UsersMapper;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersMapper maptools;
    private final UsersRepository usersRepository;

    public List<UsersDTO> findAll() {
        return maptools.mapear(usersRepository.findAll());
    }

    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

//    public UsersDTO findByLogin(String login) {
//        return maptools.mapear(usersRepository.findByLoginEquals(login));
//    }

    public Users findByLogin(String login) {
        return usersRepository.findByLoginEquals(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(login);

        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}