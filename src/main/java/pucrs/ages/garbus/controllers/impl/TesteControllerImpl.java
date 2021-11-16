package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.TesteController;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.sample.SampleDTO;
import pucrs.ages.garbus.sample.SampleService;
import pucrs.ages.garbus.services.BuildingsService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TesteControllerImpl implements TesteController {

    @Resource
    private SampleService service;

    @Resource
    BuildingsService buildingsService;

    @Override
    public ResponseEntity<Void> save(@RequestBody SampleDTO sampleDTO) {
        try {
            log.info(sampleDTO.toString());
            service.save(sampleDTO);
        } catch (ParseException pe) {
            return new ResponseEntity<>(null, BAD_REQUEST);
        }
        return new ResponseEntity<>(null, CREATED);
    }

    @Override
    public ResponseEntity<List<BuildingsDTO>> findAll() {
        return new ResponseEntity<>(buildingsService.findAll(), OK);
    }

    @Override
    public ResponseEntity<SampleDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), OK);
    }
}
