package pucrs.ages.garbus.controllers.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pucrs.ages.garbus.controllers.BuildingsController;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.excpetion.BadRequestException;
import pucrs.ages.garbus.services.BuildingsService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BuildingsControllerImpl implements BuildingsController {

    @Resource
    BuildingsService buildingsService;

    @Override
    public ResponseEntity<List<BuildingsDTO>> findAll() {
        return new ResponseEntity<>(buildingsService.findAll(), OK);
    }

    @Override
    public ResponseEntity<BuildingsDTO> findAllById(Long id) {
        BuildingsDTO buildingsDTO = buildingsService.findById(id);
        return buildingsDTO != null
                ? new ResponseEntity<>(buildingsDTO, OK)
                : new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> saveBuilding (BuildingsDTO buildingsDTO) {
        buildingsDTO.setId(0L);
        return getResponseEntity(buildingsDTO);
    }

    @Override
    public ResponseEntity<Object> updateBuilding(BuildingsDTO buildingsDTO) {
        return getResponseEntity(buildingsDTO);
    }

    @Override
    public ResponseEntity<Object> deleteBuilding(Long id) {
        try {
            buildingsService.deleteById(id);
            return new ResponseEntity<>(OK);
        } catch (ParseException e) {
            log.error("Generic bad request delete error.", e);
            return new ResponseEntity<>(e.getMessage(), NO_CONTENT);
        } catch (Exception e) {
            log.error("Generic delete error.", e);
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> getResponseEntity(BuildingsDTO buildingsDTO) {
        try {
            buildingsDTO = buildingsService.save(buildingsDTO);
            return new ResponseEntity<>(buildingsDTO, CREATED);
        } catch (ParseException e) {
            log.error("Save Parse error.", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (BadRequestException e) {
            log.error("Save Bad request error.", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Save Generic error.", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
