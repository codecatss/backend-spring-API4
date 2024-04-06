package Oracle.Partner.Tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.repositories.OpnTrackRepository;

@Service
public class OpnTrackService {
    @Autowired
    private OpnTrackRepository opnTrackRepository;

    public OpnTrackDTO findOpnTrackById(String id){
        OpnTrack opnTrack = opnTrackRepository.findById(id).get();
        return new OpnTrackDTO(opnTrack);
    }

    public Page<OpnTrackDTO> findAllOpnTracks(Pageable pageable){
        Page<OpnTrack> opnTracks = opnTrackRepository.findAll(pageable);
        return opnTracks.map(OpnTrackDTO::new);
    }
    
    public OpnTrackDTO insertOpnTrack(OpnTrackDTO opnTrackDTO){
        if (opnTrackDTO.getName() == null || opnTrackDTO.getName().isBlank()){
            throw new RuntimeException("O nome da OPN Track é obrigatório");
        }

        OpnTrack opnTrack = new OpnTrack();
        copyDTOtoEntity(opnTrackDTO, opnTrack);

        opnTrack = opnTrackRepository.save(opnTrack);

        return new OpnTrackDTO(opnTrack);

    }

    public OpnTrackDTO updateOpnTrack(String id, OpnTrackDTO opnTrackDTO){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        copyDTOtoEntity(opnTrackDTO, opnTrack);
        opnTrack = opnTrackRepository.save(opnTrack);
        return new OpnTrackDTO(opnTrack);
    }

    public void disableOpnTrack(String id){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        opnTrack.setOpnTrackStatus(false);
        opnTrackRepository.save(opnTrack);
    }

    public void enableOpnTrack(String id){
        OpnTrack opnTrack = opnTrackRepository.findById(id).orElseThrow(
            () -> new RuntimeException("OPN Track não encontrada com o id: " + id)
            );
        opnTrack.setOpnTrackStatus(true);
        opnTrackRepository.save(opnTrack);
    }

    private void copyDTOtoEntity(OpnTrackDTO opnTrackDTO, OpnTrack opnTrack){
        opnTrack.setName(opnTrackDTO.getName());
        opnTrack.setOpnTrackStatus(opnTrackDTO.getOpnTrackStatus());
    }
}
