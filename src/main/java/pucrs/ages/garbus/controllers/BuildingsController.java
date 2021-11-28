package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.BuildingsDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/buildings")
public interface BuildingsController {

    @GetMapping
    @ApiOperation("Find all Buildings")
    ResponseEntity<List<BuildingsDTO>> findAll();

    @GetMapping("/{id}")
    @ApiOperation("Find Building by id")
    ResponseEntity<BuildingsDTO> findAllById(@PathVariable ("id") @NotNull Long id);

    @PostMapping
    @ApiOperation("Create Building")
    ResponseEntity<Object> saveBuilding(@RequestBody @NotNull BuildingsDTO buildingsDTO);

    @PutMapping()
    @ApiOperation("Update Building")
    ResponseEntity<Object> updateBuilding(@RequestBody @NotNull BuildingsDTO buildingsDTO);

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Building By Id")
    ResponseEntity<Object> deleteBuilding(@PathVariable("id") @NotNull Long id);

}
