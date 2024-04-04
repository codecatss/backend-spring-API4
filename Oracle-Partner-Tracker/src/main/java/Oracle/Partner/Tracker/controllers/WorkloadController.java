package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.services.WorkloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workload")
public class WorkloadController {

    @Autowired
    private WorkloadService workloadService;

    @GetMapping
    @Operation(summary = "Get all workloads", description = "Get all workloads")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = WorkloadDTO.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Workloads not found"
            )
    })
    public ResponseEntity<Page<WorkloadDTO>> getAllWorkloads(Pageable pageable) {
        Page<WorkloadDTO> workloads = workloadService.findAllWorkloads(pageable);
        return new ResponseEntity<>(workloads, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find Workload by ID", description = "Get a workload by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = WorkloadDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Workload not found"
            )
    })
    public ResponseEntity<WorkloadDTO> findWorkloadById(@PathVariable String id) {
        WorkloadDTO workloadDTO = workloadService.findWorkloadById(id);
        if (workloadDTO != null) {
            return new ResponseEntity<>(workloadDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public WorkloadDTO insertWorkload(@RequestBody WorkloadDTO workloadDTO){
        return workloadService.insertWorkload(workloadDTO);
    }
}
