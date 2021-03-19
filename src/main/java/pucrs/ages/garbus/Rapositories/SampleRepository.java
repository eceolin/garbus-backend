package pucrs.ages.garbus.Rapositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class SampleRepository<SampleEntity> {

    @PersistenceContext
    private EntityManager em;
//
//    public SampleEntity save(SampleEntity sampleEntity) {
//        if (sampleEntity.isNew()){
//            em.persist(sampleEntity);
//            return sampleEntity;
//        } else {
//            return em.merge(sampleEntity);
//        }
//    }
}
