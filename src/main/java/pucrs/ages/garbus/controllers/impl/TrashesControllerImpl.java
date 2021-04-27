package pucrs.ages.garbus.controllers.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import pucrs.ages.garbus.controllers.TrashesController;
import pucrs.ages.garbus.services.TrashesService;
import org.springframework.http.ResponseEntity;
import pucrs.ages.garbus.dtos.TrashesDTO;
import lombok.RequiredArgsConstructor;
import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
public class TrashesControllerImpl implements TrashesController {

    @Resource
    private TrashesService trashesService;

    @Override
    public ResponseEntity<List<TrashesDTO>> findAll() {
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
    public ResponseEntity<?> insertErrorOnTrash(Long trashId, Long typeEventId, String login) {
        try {
            trashesService.insertErrorOnTrash(trashId, typeEventId, login);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", BAD_REQUEST);
        }
        return null;
    }

}
