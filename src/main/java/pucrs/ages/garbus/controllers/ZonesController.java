package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.dtos.ZonesDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/zones")
public interface ZonesController {

    @GetMapping
    @ApiOperation("Find all zones")
    ResponseEntity<List<ZonesDTO>> findAll();

    @GetMapping("/{id}")
    @ApiOperation("Find zone by id")
    ResponseEntity<ZonesDTO> findAllById(@PathVariable("id") @NotNull Long id);

    @PostMapping
    @ApiOperation("Create Zone")
    ResponseEntity<Object> saveZone(@RequestBody @NotNull ZonesDTO zonesDTO);

    @PutMapping()
    @ApiOperation("Update Zone")
    ResponseEntity<Object> updateZone(@RequestBody @NotNull ZonesDTO zonesDTO);

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Zone By Id")
    ResponseEntity<Object> deleteZone(@PathVariable("id") @NotNull Long id);

}
