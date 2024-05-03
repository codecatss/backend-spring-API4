package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Oracle.Partner.Tracker.utils.IngestionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.repositories.OpnTrackRepository;
import Oracle.Partner.Tracker.utils.Status;

@Service
public class OpnTrackService extends CsvService<OpnTrackDTO>{

    private OpnTrackRepository opnTrackRepository;

    @Autowired
    public void setOpnTrackRepository(OpnTrackRepository opnTrackRepository) {
        this.opnTrackRepository = opnTrackRepository;
    }

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
    public List<OpnTrackDTO> mapCsvToEntities(List<String[]> csvData){
        String[] header = csvData.get(0);
        return csvData.stream().skip(1)
                .map(row -> mapRowToOpnTrack(row, header))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<OpnTrackDTO> mapRowToOpnTrack(String[] row, String[] header) {
        OpnTrackDTO opnTrackDTO = new OpnTrackDTO();
        opnTrackDTO.setIngestionOperation(IngestionOperation.CSV);

        Map<String, BiConsumer<String, OpnTrackDTO>> fieldSetterMap = new HashMap<>();
        fieldSetterMap.put("OPN Track", (value, dto) -> dto.setName(value));

        IntStream.range(0, header.length).forEach(i -> {
            BiConsumer<String, OpnTrackDTO> fieldSetter = fieldSetterMap.get(header[i]);
            if (fieldSetter != null) {
                fieldSetter.accept(row[i], opnTrackDTO);
            }
        });

        OpnTrack opnTrack = new OpnTrack();
        copyDTOtoEntity(opnTrackDTO, opnTrack);

        return this.insertOpnTrack(opnTrackDTO).isPresent() ? Optional.empty() : Optional.of(new OpnTrackDTO(opnTrack));
    }
}
