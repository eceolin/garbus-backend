package pucrs.ages.garbus.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import pucrs.ages.garbus.dtos.TrashesDTO;
import pucrs.ages.garbus.dtos.TrashesListDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;
import pucrs.ages.garbus.entities.TypesEvents;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/trashes")
public interface TrashesController {

    @GetMapping
    @ApiOperation("Find all trashes")
    ResponseEntity<TrashesListDTO> findAll();

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

}
