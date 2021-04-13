package pucrs.ages.garbus.services;

import pucrs.ages.garbus.repositories.BuildingsRepository;
import pucrs.ages.garbus.mappers.BuildingsMapper;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.dtos.BuildingsDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingsService {

    private final BuildingsMapper maptools;
    private final BuildingsRepository repository;

    public List<BuildingsDTO> findAll() {
        return maptools.mapear(repository.findAll());
    }

}