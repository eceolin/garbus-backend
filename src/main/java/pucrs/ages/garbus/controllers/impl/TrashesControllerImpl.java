package pucrs.ages.garbus.controllers.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import pucrs.ages.garbus.controllers.TrashesController;
import pucrs.ages.garbus.services.TrashesService;
import org.springframework.http.ResponseEntity;
import pucrs.ages.garbus.dtos.TrashesDTO;
import lombok.RequiredArgsConstructor;
import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
public class TrashesControllerImpl implements TrashesController {

    @Resource
    private TrashesService TrashesService;

    @Override
    public ResponseEntity<List<TrashesDTO>> findAll() {
        return new ResponseEntity<>(TrashesService.findAll(), OK);
    }

    @Override
    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<TrashesDTO>> findAllByZone(@PathVariable Long zoneId) {
        return new ResponseEntity<>(TrashesService.findAllByZonesId(zoneId), OK);
    }

    @Override
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<TrashesDTO>> findAllByBuilding(@PathVariable Long buildingId){
        return new ResponseEntity<>(TrashesService.findAllByBuildingId(buildingId), OK);
    }

    @Override
    @GetMapping("/trash/{trashId}")
    public ResponseEntity<List<TrashesDTO>> findDetailsByTrash(@PathVariable Long trashId){
        return new ResponseEntity<>(TrashesService.findDetailsByTrashId(trashId), OK);
    }

}
