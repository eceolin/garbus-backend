package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.mappers.UsersMapper;
import pucrs.ages.garbus.repositories.ProfilesRepository;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersMapper maptools;
    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

//    public UsersDTO findByLogin(String login) {
//        return maptools.mapear(usersRepository.findByLoginEquals(login));
//    }

    public Users findByLoginEquals(String login) {
        return usersRepository.findByLoginEquals(login);
    }

    public Users findByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public Users save(UsersRequestDTO user) {
        Profiles profiles = profilesRepository.findById(user.getIdProfile()).orElseThrow(NotFoundException::new);
        return usersRepository.save(new Users(user, profiles));
    }

    public void deleteUser (Long idUser) {
        Users user = findUser(idUser);
        usersRepository.delete(user);
    }

    public Users updateUser (Long idUser, UsersRequestDTO usersRequestDTO) {
        Users users = findUser(idUser);
        users.updateBy(usersRequestDTO);
        return usersRepository.save(users);
    }

    public Users findUserById (Long idUser) {
        return findUser(idUser);
    }

    private Users findUser(Long idUser) {
        return usersRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Error"));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(login);

        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}