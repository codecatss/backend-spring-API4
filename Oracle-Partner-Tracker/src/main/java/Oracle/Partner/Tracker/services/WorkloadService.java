package Oracle.Partner.Tracker.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Oracle.Partner.Tracker.dto.GenericDTO;
import Oracle.Partner.Tracker.dto.PartnerDTO;
import Oracle.Partner.Tracker.utils.ChangeType;
import Oracle.Partner.Tracker.utils.IngestionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.repositories.WorkloadRepository;
import Oracle.Partner.Tracker.utils.Status;

@Service
public class WorkloadService implements GenericService{

    @Autowired
    private WorkloadRepository workloadRepository;

    @Autowired
    private ChangeHistoryService changeHistoryService;

//    @Autowired
//    public void setWorkloadRepository(WorkloadRepository workloadRepository) {
//        this.workloadRepository = workloadRepository;
//    }

    public Optional<WorkloadDTO> findWorkloadById(Long id){
        Optional<Workload> workload = workloadRepository.findById(id);
        return Optional.ofNullable(workload).orElse(null).map(WorkloadDTO::new);
    }

    public Optional<WorkloadDTO> findWorkloadByName(String name){
        Optional<Workload> workload = Optional.ofNullable(workloadRepository.findByName(name));
        return Optional.ofNullable(workload).orElse(null).map(WorkloadDTO::new);
    }

    public Page<WorkloadDTO> findAllWorkloads(Pageable pageable){
        Page<Workload> workloads = workloadRepository.findAll(pageable);
        return workloads.map(WorkloadDTO::new);
    }
    
    public Optional<WorkloadDTO> insertWorkload(WorkloadDTO workloadDTO){
        Optional<WorkloadDTO> optionalWorkload= this.findWorkloadByName(workloadDTO.getName());
        if (optionalWorkload.isPresent()){
            return Optional.empty();
        }
        if (workloadDTO.getName() == null || workloadDTO.getName().isBlank()){
            throw new RuntimeException("O nome da Workload é obrigatório");
        }

        Workload workload = new Workload();
        copyDTOtoEntity(workloadDTO, workload);

        workload = workloadRepository.save(workload);

        return Optional.of(new WorkloadDTO(workload));

    }

    public WorkloadDTO updateWorkload(Long id, WorkloadDTO workloadDTO){
        Workload workload = workloadRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Workload não encontrada com o id: " + id)
            );
        copyDTOtoEntity(workloadDTO, workload);
        workload = workloadRepository.save(workload);
        return new WorkloadDTO(workload);
    }

    public void disableWorkload(Long id){
        Workload workload = workloadRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Workload não encontrada com o id: " + id)
            );
        workload.setStatus(Status.INACTIVE);
        workloadRepository.save(workload);
    }

    public void enableWorkload(Long id){
        Workload workload = workloadRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Workload não encontrada com o id: " + id)
            );
        workload.setStatus(Status.ACTIVE);
        workloadRepository.save(workload);
    }

    private void copyDTOtoEntity(WorkloadDTO workloadDTO, Workload workload){
        workload.setName(workloadDTO.getName());
        workload.setIngestionOperation(workloadDTO.getIngestionOperation());
        if (workloadDTO.getStatus() == null || workloadDTO.getStatus().name().isBlank()){
            workloadDTO.setStatus(Status.ACTIVE);
        }
        workload.setStatus(workloadDTO.getStatus());
        if(workloadDTO.getCreateAt() == null || workloadDTO.getCreateAt().toString().isBlank()){
            workload.setCreateAt(LocalDateTime.now());
        }else{
            workload.setCreateAt(workloadDTO.getCreateAt());
        }
        if(
            workloadDTO.getDescription() == null || workloadDTO.getDescription().isBlank()){
            workload.setDescription("No description");
            }else{
                workload.setDescription(workloadDTO.getDescription());
            }
        workload.setUpdateAt(LocalDateTime.now());
    }

    @Override
    public Class<?> getDtoClass() {
        return WorkloadDTO.class;
    }

    @Override
    public void saveAllGenericDTO(List<GenericDTO> genericDTOList) {
        for(GenericDTO genericDTO : genericDTOList){
            WorkloadDTO workloadDTO = (WorkloadDTO) genericDTO;
            if(workloadRepository.findByName(workloadDTO.getName()) == null){
                workloadRepository.save(new Workload(workloadDTO));
                changeHistoryService.saveChangeHistory(Long.decode("1"),"workload", ChangeType.CREATE, new WorkloadDTO(), workloadDTO);
            }
        }
    }

    public Optional<WorkloadDTO> mapRowToWorkload(String[] row, String[] header){
        WorkloadDTO workloadDTO = new WorkloadDTO();
        
        for (int j = 0; j < header.length; j++){
            workloadDTO.setIngestionOperation(IngestionOperation.CSV);
            switch (header[j]){
                case "Workload":
                    workloadDTO.setName(row[j]);
                    break;
                case "Workload Status":
                    workloadDTO.setStatus(Status.toStatus(row[j]));
                    break;
            }
        }

        
        Workload workload = new Workload();
        copyDTOtoEntity(workloadDTO, workload);

        Optional<WorkloadDTO> optionalWorkload = this.insertWorkload(workloadDTO);            
        if (optionalWorkload.isPresent()){
            return Optional.empty();
        }

        return Optional.of(new WorkloadDTO(workload));
    }

}
