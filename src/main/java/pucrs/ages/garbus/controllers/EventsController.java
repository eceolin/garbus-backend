package pucrs.ages.garbus.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pucrs.ages.garbus.dtos.EventsDTO;
import pucrs.ages.garbus.dtos.TypesEventsDTO;

import java.util.List;

@RequestMapping("/events")
public interface EventsController {

    @GetMapping
    @ApiOperation("Find all trashes")
    ResponseEntity<List<EventsDTO>> findAll();

    @GetMapping(path = "/types")
    @ApiOperation("Find all errors to all trashes")
    ResponseEntity<List<TypesEventsDTO>> findAllTypes();

    @GetMapping(path = "/types/errors")
    @ApiOperation("Find all errors to all trashes")
    ResponseEntity<List<TypesEventsDTO>> findAllTypesErrors();
}
