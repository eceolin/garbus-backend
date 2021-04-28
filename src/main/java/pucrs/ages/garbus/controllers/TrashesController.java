package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pucrs.ages.garbus.dtos.SimplifiedTrashesWithThresholdsDTO;
import pucrs.ages.garbus.dtos.TrashDetailsDTO;
import pucrs.ages.garbus.dtos.TrashesDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/trashes")
public interface TrashesController {

    @GetMapping
    @ApiOperation("Find all trashes")
    ResponseEntity<List<TrashesDTO>> findAll();

    @GetMapping("/zone/{zoneId}")
    @ApiOperation("Find all on zone")
    ResponseEntity<List<TrashesDTO>> findAllByZone(Long id);

    @GetMapping(path = "{trashId}/errors")
    @ApiOperation("Find errors by trash id")
    ResponseEntity<List<TrashesDTO>> findErrorsByTrashId(@NotNull @PathVariable("trashId") String trashId);

    @PostMapping(path = "{trashId}/errors/{typeEventId}/users/{login:.+}")
    @ApiOperation("Find errors by trash id")
    ResponseEntity<?> insertErrorOnTrash(@NotNull @PathVariable("trashId") Long trashId,
                                         @NotNull @PathVariable("typeEventId") Long typeEventId,
                                         @NotNull @PathVariable("login") String userId);

    @GetMapping("/building/{buildingId}")
    @ApiOperation("Find trashes by building")
    ResponseEntity<List<SimplifiedTrashesWithThresholdsDTO>> findAllByBuilding(Long id);

    @GetMapping("/trash/{trashId}")
    @ApiOperation("Find trash by trashId")
    ResponseEntity<TrashDetailsDTO> findTrashById(Long id);

}
