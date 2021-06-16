package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;
import pucrs.ages.garbus.entities.Profiles;
import pucrs.ages.garbus.entities.UserZone;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.mappers.UsersMapper;
import pucrs.ages.garbus.repositories.ProfilesRepository;
import pucrs.ages.garbus.repositories.UserZoneRepository;
import pucrs.ages.garbus.repositories.UsersRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersMapper maptools;
    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;
    private final UserZoneRepository userZoneRepository;

    public List<UsersDTO> findAll() {
        List<Users> users = usersRepository.findAll();
        List<UsersDTO> usersDTO = users.stream()
                .map(UsersDTO::of)
                .collect(Collectors.toList());
        buscarZonasUsuarios(usersDTO);
        return usersDTO;
    }

    private void buscarZonasUsuarios(List<UsersDTO> users) {
        users.forEach(user -> user.setZone(userZoneRepository.findUserZoneByUsersId(user.getId()).getZones()));
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

    @Transactional
    public void deleteUser (Long idUser) {
        userZoneRepository.deleteByUsersId(idUser);
        usersRepository.deleteById(idUser);
    }

    public Users updateUser (Long idUser, UsersRequestDTO usersRequestDTO) {
        Users user = usersRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Error"));
        user.updateBy(usersRequestDTO);
        return usersRepository.save(user);
    }

    public UsersDTO findUserById (Long idUser) {
        return findUser(idUser);
    }

    private UsersDTO findUser(Long idUser) {
        Users user = usersRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Error"));
        UsersDTO usersDTO = UsersDTO.of(user);
        buscarZonasUsuarios(Collections.singletonList(usersDTO));
        return usersDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = usersRepository.findByLogin(login);

        return new User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }
}