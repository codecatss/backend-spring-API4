package Oracle.Partner.Tracker.services;

import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExpertiseService {
    @Autowired
    ExpertiseRepository expertiseRepository;

    public void saveExpertise(ExpertiseDTO expertiseDTO){
        expertiseRepository.save(new Expertise(expertiseDTO));
    }

    public List<Expertise> findAllExpertise(){
        return expertiseRepository.findAll();
    }
}
