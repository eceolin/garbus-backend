package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.TrashesController;
import pucrs.ages.garbus.dtos.*;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.excpetion.NotFoundException;
import pucrs.ages.garbus.services.TrashesService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequiredArgsConstructor
public class TrashesControllerImpl implements TrashesController {

    @Resource
    private TrashesService trashesService;

    @Override
    public ResponseEntity<TrashesListDTO> findAll() {
        return new ResponseEntity<>(trashesService.findAll(), OK);
    }

    @Override
    public ResponseEntity<List<TrashesDTO>> findAllByZone(@PathVariable Long zoneId) {
        return new ResponseEntity<>(trashesService.findAllByZonesId(zoneId), OK);
    }

    @Override
    public ResponseEntity<List<TrashesDTO>> findErrorsByTrashId(String trashId) {
        return null;
    }

    @Override
    public ResponseEntity<?> insertErrorInTrash(ErrorRequest errorRequest) {
        try {
            trashesService.insertErrorInTrash(errorRequest);
            return new ResponseEntity<>("Evento criado!", CREATED);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), BAD_REQUEST);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getError(), NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateStatus(@Valid ErrorRequest errorRequest) {
        trashesService.updateStatusToActive(errorRequest);
        return new ResponseEntity<>("Lixeira agora está ativa!", CREATED);
    }

    @Override
    public ResponseEntity<List<TrashesReduceDTO>> findAllByBuilding(@PathVariable Long buildingId) {
        return new ResponseEntity<>(trashesService.findAllByBuildingId(buildingId), OK);
    }

    @Override
    public ResponseEntity<TrashDetailsDTO> findTrashById(@PathVariable Long trashId) {
        return new ResponseEntity<>(trashesService.findTrashById(trashId), OK);
    }

    @Override
    public ResponseEntity<TrashesListDTO> findAllByStatus(Long statusId) {
        return new ResponseEntity<>(trashesService.findAllByStatusId(statusId), OK);
    }

}


