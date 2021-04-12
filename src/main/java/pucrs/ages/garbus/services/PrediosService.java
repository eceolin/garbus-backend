package pucrs.ages.garbus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pucrs.ages.garbus.entities.Predios;
import pucrs.ages.garbus.repositories.PrediosRepository;
import pucrs.ages.garbus.sample.SampleDTO;
import pucrs.ages.garbus.sample.SampleEntity;
import pucrs.ages.garbus.sample.SampleMapper;
import pucrs.ages.garbus.sample.SampleRepository;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrediosService {

    private final SampleMapper mapper;
    private final PrediosRepository repository;


    public List<Predios> findAll() {
        List<Predios> sampleEntities = repository.findAll();
        return sampleEntities;
    }

}
