package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.repositories.OpnTrackRepository;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.utils.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OpnTrackService implements GenericService{
    @Autowired
    private OpnTrackRepository opnTrackRepository;

    @Autowired
    private ChangeHistoryService changeHistoryService;

//    @Autowired
//    public void setOpnTrackRepository(OpnTrackRepository opnTrackRepository) {
//        this.opnTrackRepository = opnTrackRepository;
//    }

    public Optional<OpnTrackDTO> findOpnTrackById(Long id){
        Optional<OpnTrack> opnTrack = opnTrackRepository.findById(id);
        return Optional.ofNullable(opnTrack).orElse(null).map(OpnTrackDTO::new);
    }

    public Optional<OpnTrackDTO> findOpnTrackByName(String name){
        Optional<OpnTrack> opnTrack = Optional.ofNullable(opnTrackRepository.findByName(name));
        return Optional.ofNullable(opnTrack).orElse(null).map(OpnTrackDTO::new);
    }

    public Page<OpnTrackDTO> findAllOpnTracks(Pageable pageable){
        Page<OpnTrack> opnTracks = opnTrackRepository.findAll(pageable);
        return opnTracks.map(OpnTrackDTO::new);
    }
    
    public Optional<OpnTrackDTO> insertOpnTrack(OpnTrackDTO opnTrackDTO){
        Optional<OpnTrackDTO> optionalOpnTrack= this.findOpnTrackByName(opnTrackDTO.getName());
        if (optionalOpnTrack.isPresent()){
            return Optional.empty();
        }
        if (opnTrackDTO.getName() == null || opnTrackDTO.getName().isBlank()){
            throw new RuntimeException("O nome da OPN Track é obrigatório");
        }

        OpnTrack opnTrack = new OpnTrack();
        copyDTOtoEntity(opnTrackDTO, opnTrack);

        opnTrack = opnTrackRepository.save(opnTrack);

        return Optional.of(new OpnTrackDTO(opnTrack));

    }

    public OpnTrackDTO updateOpnTrack(Long id, OpnTrackDTO opnTrackDTO){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        copyDTOtoEntity(opnTrackDTO, opnTrack);
        opnTrack = opnTrackRepository.save(opnTrack);
        return new OpnTrackDTO(opnTrack);
    }

    public void disableOpnTrack(Long id){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        opnTrack.setStatus(Status.INACTIVE);
        opnTrackRepository.save(opnTrack);
    }

    public void enableOpnTrack(Long id){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        opnTrack.setStatus(Status.ACTIVE);
        opnTrackRepository.save(opnTrack);
    }

    private void copyDTOtoEntity(OpnTrackDTO opnTrackDTO, OpnTrack opnTrack){
        opnTrack.setName(opnTrackDTO.getName());
        opnTrack.setIngestionOperation(opnTrackDTO.getIngestionOperation());
        if (opnTrackDTO.getStatus() == null || opnTrackDTO.getStatus().name().isBlank()){
            opnTrackDTO.setStatus(Status.ACTIVE);
        }
        opnTrack.setStatus(opnTrackDTO.getStatus());
        if(opnTrackDTO.getCreateAt() == null || opnTrackDTO.getCreateAt().toString().isBlank()){
            opnTrack.setCreateAt(LocalDateTime.now());
        }else{
            opnTrack.setCreateAt(opnTrackDTO.getCreateAt());
        }
        opnTrack.setUpdateAt(LocalDateTime.now());
    }

    @Override
    public Class<?> getDtoClass() {
        return OpnTrackDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            OpnTrackDTO opnTrackDTO = (OpnTrackDTO) genericDTO;
            if(opnTrackRepository.findByName(opnTrackDTO.getName()) == null){
                opnTrackRepository.save(new OpnTrack(opnTrackDTO));
            }
        }
    }

    public Optional<OpnTrackDTO> mapRowToOpnTrack(String[] row, String[] header){
        OpnTrackDTO opnTrackDTO = new OpnTrackDTO();
        
        for (int j = 0; j < header.length; j++){
            opnTrackDTO.setIngestionOperation(IngestionOperation.CSV);
            switch (header[j]){
                case "OPN Track":
                    opnTrackDTO.setName(row[j]);
                    break;
                case "OpnTrack Status":
                    opnTrackDTO.setStatus(Status.toStatus(row[j]));
                    break;
            }
        }

        
        OpnTrack opnTrack = new OpnTrack();
        copyDTOtoEntity(opnTrackDTO, opnTrack);

        Optional<OpnTrackDTO> optionalOpnTrack = this.insertOpnTrack(opnTrackDTO);            

        if (optionalOpnTrack.isPresent()){
            return Optional.empty();
        }

        return Optional.of(new OpnTrackDTO(opnTrack));
    }

}
