package Oracle.Partner.Tracker.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertiseService {
    @Autowired
    private ExpertiseRepository expertiseRepository;

    public void saveExpertise(ExpertiseDTO expertiseDTO){
        expertiseRepository.save(new Expertise(expertiseDTO));
    }

    public List<Expertise> findAllExpertise(){
        return expertiseRepository.findAll();
    }

    public Expertise findExpertiseById(String id){
        return expertiseRepository.findById(id).orElse(null);
    }

}
