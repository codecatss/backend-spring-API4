package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.repositories.WorkloadRepository;
import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WorkloadService {

    @Autowired
    private WorkloadRepository workloadRepository;

    public WorkloadDTO findWorkloadById(Long id) {
        Workload workload = workloadRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Workload not found with id: " + id));
        return new WorkloadDTO(workload);
    }

    public Optional<WorkloadDTO> findWorkloadByName(String name) {
        Workload workload = workloadRepository.findByName(name);
        return Optional.ofNullable(workload).map(WorkloadDTO::new);
    }

    public Page<WorkloadDTO> findAllWorkloads(Pageable pageable) {
        Page<Workload> workloads = workloadRepository.findAll(pageable);
        return workloads.map(WorkloadDTO::new);
    }

    public WorkloadDTO insertWorkload(WorkloadDTO workloadDTO) {
        if (workloadDTO.getName() == null || workloadDTO.getName().isBlank()) {
            throw new RuntimeException("The name of the workload is required");
        }

        Workload workload = new Workload();
        copyDTOtoEntity(workloadDTO, workload);

        workload = workloadRepository.save(workload);

        return new WorkloadDTO(workload);
    }

    public WorkloadDTO updateWorkload(Long id, WorkloadDTO workloadDTO) {
        Workload workload = workloadRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Workload not found with id: " + id));
        copyDTOtoEntity(workloadDTO, workload);
        workload = workloadRepository.save(workload);
        return new WorkloadDTO(workload);
    }

    public void deleteWorkload(Long id) {
        Workload workload = workloadRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Workload not found with id: " + id));
        workloadRepository.delete(workload);
    }

    private void copyDTOtoEntity(WorkloadDTO workloadDTO, Workload workload) {
        workload.setName(workloadDTO.getName());
        workload.setDescription(workloadDTO.getDescription());
        workload.setWorkloadStatus(workloadDTO.getWorkloadStatus());
        workload.setCreatedAt(LocalDateTime.now());
        workload.setIngestionOperation(workloadDTO.getIngestionOperation());
    }
}
