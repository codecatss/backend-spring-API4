package Oracle.Partner.Tracker.services;


import Oracle.Partner.Tracker.dto.ExpertiseRecord;
import Oracle.Partner.Tracker.dto.GenericDTO;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;

import Oracle.Partner.Tracker.entities.Partner;

import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;

import Oracle.Partner.Tracker.utils.ChangeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void saveExpertise(ExpertiseRecord expertiseRecord){
        expertiseRepository.save(new Expertise(expertiseRecord));
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

    public void updateExpertise(Long id, ExpertiseDTO newExpertiseDTO){
        Expertise oldExpertise = expertiseRepository.findById(id).get();

        Expertise expertise = new Expertise(newExpertiseDTO);
        expertise.setId(id);
        expertise.setCreateAt(oldExpertise.getCreateAt());

        newExpertiseDTO.setCreateAt(expertise.getCreateAt());

        ExpertiseDTO oldExpertiseDTO = new ExpertiseDTO(oldExpertise);

        Partner partner = new Partner();
        partner.setId(Long.decode("1"));
        changeHistoryService.saveChangeHistory(partner, id, "expertise", ChangeType.UPDATE, oldExpertiseDTO, newExpertiseDTO);
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
