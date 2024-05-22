package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertiseService implements GenericService{

    @Autowired
    private ExpertiseRepository expertiseRepository;

    @Autowired
    private ChangeHistoryService changeHistoryService;

    public void saveExpertise(ExpertiseDTO expertiseDTO){
        expertiseRepository.save(new Expertise(expertiseDTO));
    }

    public List<Expertise> findAllExpertise(){
        return expertiseRepository.findAll();
    }

    public Expertise findExpertiseById(Long id){
        return expertiseRepository.findById(id).orElse(null);
    }

    public ExpertiseDTO findExpertiseDtoById(Long id){
        return new ExpertiseDTO(findExpertiseById(id));

    }

    public void updateExpertise(Long id, ExpertiseDTO expertiseDTO){
        Expertise expertise = new Expertise(expertiseDTO);
        expertise.setId(id);
        expertiseRepository.save(expertise);
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
