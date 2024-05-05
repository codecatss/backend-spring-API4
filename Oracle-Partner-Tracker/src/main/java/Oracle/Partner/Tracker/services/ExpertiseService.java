package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.GenericDTO;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExpertiseService implements GenericService{
    private ExpertiseRepository expertiseRepository;

    @Autowired
    public void setExpertiseRepository(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    public void saveExpertise(ExpertiseDTO expertiseDTO){
        expertiseRepository.save(new Expertise(expertiseDTO));
    }

    public List<Expertise> findAllExpertise(){
        return expertiseRepository.findAll();
    }

    public Expertise findExpertiseById(String id){
        return expertiseRepository.findById(id).orElse(null);
    }

    @Override
    public Class<?> getDtoClass() {
        return ExpertiseDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            ExpertiseDTO expertiseDTO = (ExpertiseDTO) genericDTO;
            if(expertiseRepository.findByName(expertiseDTO.getName()) == null){
                expertiseRepository.save(new Expertise(expertiseDTO));
            }
        }
    }
}
