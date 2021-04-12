package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.controllers.TesteController;
import pucrs.ages.garbus.entities.Predios;
import pucrs.ages.garbus.sample.SampleDTO;
import pucrs.ages.garbus.sample.SampleService;
import pucrs.ages.garbus.services.PrediosService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class TesteControllerImpl implements TesteController {

    @Resource
    private SampleService service;

    @Resource
    PrediosService prediosService;

    @Override
    public ResponseEntity<Void> save(@RequestBody SampleDTO sampleDTO) {
        try {
            System.out.println(sampleDTO);
            service.save(sampleDTO);
        } catch (ParseException pe) {
            return new ResponseEntity<>(null, BAD_REQUEST);
        }
        return new ResponseEntity<>(null, CREATED);
    }

    @Override
    public ResponseEntity<List<Predios>> findAll() {
        return new ResponseEntity<>(prediosService.findAll(), OK);
    }

    @Override
    public ResponseEntity<SampleDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), OK);
    }
}
