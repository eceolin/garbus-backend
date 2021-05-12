package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.ZonesDTO;
import pucrs.ages.garbus.entities.Zones;
import pucrs.ages.garbus.mappers.ZonesMapper;
import pucrs.ages.garbus.repositories.ZonesRepository;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZonesService {

    private final ZonesMapper mapper;
    private final ZonesRepository repository;

    public List<ZonesDTO> findAll() {
        List<Zones> zones = repository.findAll();
        return mapper.entityToDTO(zones);
    }

    public ZonesDTO findById(Long id) {
        Zones source = repository.findById(id).orElse(null);
        return mapper.entityToDTO(source);
    }

    public void save(final ZonesDTO zonesDTO) throws ParseException {
        Zones zones = mapper.dtoToEntity(zonesDTO);
        repository.saveAndFlush(zones);
    }

    public void deleteById(Long id) throws ParseException {
        repository.deleteById(id);
    }
    
}
