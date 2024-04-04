package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.repositories.WorkloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkloadService {

    @Autowired
    private WorkloadRepository workloadRepository;

    @Transactional(readOnly = true)
    public WorkloadDTO findWorkloadById(String id) {
        Workload workload = workloadRepository.findById(id).get();
        return new WorkloadDTO(workload);
    }

    @Transactional(readOnly = true)
    public Page<WorkloadDTO> findAllWorkloads(Pageable pageable) {
        Page<Workload> workloads = workloadRepository.findAll(pageable);
        return workloads.map(WorkloadDTO::new);
    }

    @Transactional
    public WorkloadDTO insertWorkload(WorkloadDTO workloadDTO) {

        if (workloadDTO.getName() == null || workloadDTO.getName().isBlank()) {
            throw new IllegalArgumentException("O nome do workload é obrigatório.");
        }
        Workload workload = new Workload();
        workload.setName(workloadDTO.getName());
        workload.setDescription(workloadDTO.getDescription());
        workload.setCreated_On(LocalDateTime.now());
        workload = workloadRepository.save(workload);
        return new WorkloadDTO(workload);
    }
}
