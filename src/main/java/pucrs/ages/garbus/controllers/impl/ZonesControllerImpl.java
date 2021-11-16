package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pucrs.ages.garbus.controllers.ZonesController;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.services.ZonesService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ZonesControllerImpl implements ZonesController {

    @Resource
    ZonesService zonesService;

    @Override
    public ResponseEntity<List<ZonesDTO>> findAll() {
        return new ResponseEntity<>(zonesService.findAll(), OK);
    }

    @Override
    public ResponseEntity<ZonesDTO> findAllById(Long id) {
        ZonesDTO zonesDTO = zonesService.findById(id);
        return zonesDTO != null
                ? new ResponseEntity<>(zonesDTO, OK)
                : new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> saveZone(ZonesDTO zonesDTO) {
        return getResponseEntity(zonesDTO);
    }

    @Override
    public ResponseEntity<Object> updateZone(ZonesDTO zonesDTO) {
        return getResponseEntity(zonesDTO);
    }

    @Override
    public ResponseEntity<Object> deleteZone(Long id) {
        try {
            zonesService.deleteById(id);
            return new ResponseEntity<>(OK);
        } catch (ParseException e) {

            log.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), NO_CONTENT);
        } catch (Exception e) {

            log.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> getResponseEntity(ZonesDTO zonesDTO) {
        try {
            zonesService.save(zonesDTO);
            return new ResponseEntity<>(zonesDTO, CREATED);
        } catch (ParseException | BadRequestException e) {
            log.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
