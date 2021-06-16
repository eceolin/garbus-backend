package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.UserController;
import pucrs.ages.garbus.dtos.UsersDTO;
import pucrs.ages.garbus.dtos.UsersRequestDTO;
import pucrs.ages.garbus.entities.Users;
import pucrs.ages.garbus.services.UsersService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Resource
    private UsersService usersService;

    @Override
    public List<UsersDTO> findAll() {
        return usersService.findAll();
    }

    @Override
    public UsersDTO saveUser(@Valid UsersRequestDTO usersRequestDTO) {
        Users users = usersService.save(usersRequestDTO);
        return new UsersDTO(users);
    }

    @Override
    public void deleteUser(Long idUser) {
        usersService.deleteUser(idUser);
    }

    @Override
    public UsersDTO findUserById(Long idUser) {
        return usersService.findUserById(idUser);
    }

    @Override
    public UsersDTO updateUser(@Valid UsersRequestDTO usersRequestDTO, @PathVariable Long idUser) {
        Users users = usersService.updateUser(idUser, usersRequestDTO);
        return new UsersDTO(users);
    }


}
