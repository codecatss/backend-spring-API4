package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.PartnerDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.entities.Partner;
import Oracle.Partner.Tracker.repositories.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService implements GenericService{

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> findAllUsers() {
        List<Partner> allPartners = partnerRepository.findAll();
        if (allPartners.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return allPartners;
    }

    public Partner findUserById(Long id) {
        Optional<Partner> user = partnerRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public Partner registerNewPartner(PartnerDTO partnerDTO) {
        if (partnerRepository.existsByUsername(partnerDTO.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return partnerRepository.save(new Partner(partnerDTO));
    }

    public void updateUser(Long id, PartnerDTO partnerDTO){
        Optional<Partner> partner = partnerRepository.findById(id);
        if (partner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Partner partnerUpdate = partner.get();
        partnerUpdate.setUpdateAt(LocalDateTime.now());

        // Atualizar o Partner
    }

    public void deleteUser(Long id) {
        Optional<Partner> partner = partnerRepository.findById(id);
        if (partner.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        partnerRepository.deleteById(id);
    }

    @Override
    public Class<?> getDtoClass() {
        return PartnerDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            PartnerDTO partnerDTO = (PartnerDTO) genericDTO;
            if(!partnerRepository.existsByUsername(partnerDTO.getUsername()) & partnerDTO.getUsername() != null){
                partnerRepository.save(new Partner(partnerDTO));
            }
        }
    }
}
