package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/trashes")
public interface TrashesController {

    @GetMapping
    @ApiOperation("Find all trashes")
    ResponseEntity<TrashesListDTO> findAll();


    @GetMapping("/zone/{zoneId}")
    @ApiOperation("Find all trashes on zone")
    ResponseEntity<List<TrashesDTO>> findAllByZone(Long id);

    @GetMapping(path = "{trashId}/errors")
    @ApiOperation("Find errors by trash id")
    ResponseEntity<List<TrashesDTO>> findErrorsByTrashId(@NotNull @PathVariable("trashId") String trashId);

    @PostMapping(path = "/send-problem-report")
    @ApiOperation("Send problem report")
    ResponseEntity<Object> insertErrorInTrash(@RequestBody @Valid TrashProblemReportDTO trashProblemReport, Authentication authentication);

    @PostMapping(path = "/reactivate")
    @ApiOperation("Reactivate trash, removing from maintenance status")
    ResponseEntity<Object> reactivate(@RequestBody @Valid TrashReactivateDTO trashReactivateDTO, Authentication authentication);

    @GetMapping("/building/{buildingId}")
    @ApiOperation("Find trashes by building")
    ResponseEntity<List<TrashesReduceDTO>> findAllByBuilding(Long id);

    @GetMapping("/{trashId}")
    @ApiOperation("Find trash by trashId")
    ResponseEntity<TrashDetailsDTO> findTrashById(Long id);

    @GetMapping("/status/{statusId}")
    @ApiOperation("Find all trashes by status id")
    ResponseEntity<TrashesListDTO> findAllByStatus(@NotNull @PathVariable("statusId") Long statusId);

}
