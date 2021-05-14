package pucrs.ages.garbus.services;

import pucrs.ages.garbus.repositories.BuildingsRepository;
import pucrs.ages.garbus.mappers.BuildingsMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import pucrs.ages.garbus.entities.Buildings;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class BuildingsService {

    private final BuildingsMapper maptools;
    private final BuildingsRepository repository;

    public List<BuildingsDTO> findAll() {
        return maptools.mapear(repository.findAll());
    }
    
    public BuildingsDTO findById(Long id) {
        Buildings source = repository.findById(id).orElse(null);
        return maptools.entityToDTO(source);
    }

    public void save(final BuildingsDTO buildingsDTO) throws ParseException {
        Buildings buildings = maptools.mapearDTO(buildingsDTO);
        repository.saveAndFlush(buildings);
    }

    public void deleteById(Long id) throws ParseException {
        repository.deleteById(id);
    }

}