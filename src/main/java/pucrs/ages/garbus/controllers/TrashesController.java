package pucrs.ages.garbus.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import pucrs.ages.garbus.dtos.TrashesDTO;

import java.util.List;

@RequestMapping("/trashes")
public interface TrashesController {

    @GetMapping
    @ApiOperation("Find all samples")
    ResponseEntity<List<TrashesDTO>> findAll();

    @GetMapping
    @ApiOperation("Find all on zone")
    ResponseEntity<List<TrashesDTO>> findAllByZone(Long id);

    @GetMapping
    @ApiOperation("Find trash by building")
    ResponseEntity<List<TrashesDTO>> findAllByBuilding(Long buildingId);

    @GetMapping
    @ApiOperation("Find trash details by trash")
    ResponseEntity<List<TrashesDTO>> findDetailsByTrash(Long trashId);
}
