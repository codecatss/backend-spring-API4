package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.services.WorkloadService;
import Oracle.Partner.Tracker.utils.companyEnum.IngestionOperation;
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

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workload")
public class WorkloadController {

    @Autowired
    private WorkloadService workloadService;

    @GetMapping
    @Operation(summary = "Get all Workloads", description = "Retrieve all Workloads")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Workloads retrieved",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Workload.class)
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Workloads not found")
    })
    public ResponseEntity<Page<WorkloadDTO>> getAllWorkloads(Pageable pageable) {
        Page<WorkloadDTO> workloads = workloadService.findAllWorkloads(pageable);
        return new ResponseEntity<>(workloads, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get Workload by ID", description = "Retrieve a Workload by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Workload retrieved",
                    content = @Content(
                            schema = @Schema(implementation = WorkloadDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Workload not found")
    })
    public ResponseEntity<WorkloadDTO> getWorkloadById(@PathVariable Long id) {
        WorkloadDTO workloadDTO = workloadService.findWorkloadById(id);
        if (workloadDTO != null) {
            return new ResponseEntity<>(workloadDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Insert Workload", description = "Insert a new Workload")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Workload inserted",
                    content = @Content(
                            schema = @Schema(implementation = WorkloadDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Workload already exists"
            )
    })
    public ResponseEntity<WorkloadDTO> insertWorkload(@RequestBody WorkloadDTO workloadDTO) {
        Optional<WorkloadDTO> optionalWorkload = workloadService.findWorkloadByName(workloadDTO.getName());
        if (optionalWorkload.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        workloadDTO = workloadService.insertWorkload(workloadDTO);
        URI uri = URI.create("/workload/" + workloadDTO.getId());
        return ResponseEntity.created(uri).body(workloadDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update Workload", description = "Update an existing Workload")
    @ApiResponse(
            responseCode = "200",
            description = "Workload updated",
            content = @Content(
                    schema = @Schema(implementation = WorkloadDTO.class)
            )
    )
    public ResponseEntity<WorkloadDTO> updateWorkload(@PathVariable Long id, @RequestBody WorkloadDTO workloadDTO) {
        workloadDTO = workloadService.updateWorkload(id, workloadDTO);
        return new ResponseEntity<>(workloadDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete Workload", description = "Delete an existing Workload")
    @ApiResponse(
            responseCode = "204",
            description = "Workload deleted"
    )
    public ResponseEntity<Void> deleteWorkload(@PathVariable Long id) {
        workloadService.deleteWorkload(id);
        return ResponseEntity.noContent().build();
    }
}
