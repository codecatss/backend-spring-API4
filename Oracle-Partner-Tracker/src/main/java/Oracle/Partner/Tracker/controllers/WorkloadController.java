package Oracle.Partner.Tracker.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Oracle.Partner.Tracker.dto.WorkloadDTO;
import Oracle.Partner.Tracker.entities.Workload;
import Oracle.Partner.Tracker.services.WorkloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/workload")
public class WorkloadController {
    
    @Autowired
    private WorkloadService workloadService;
    
    @GetMapping
    @Operation(summary = "Get all Workloads", description = "Get all Workloads")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(
                array = @ArraySchema(
                    schema = @Schema(implementation = Workload.class)
                )
            ),
            description = "Workloads retrieved"
        ),
        @ApiResponse(responseCode = "404", description = "Workloads not found")
    })
    public ResponseEntity<Page<WorkloadDTO>> getAllWorkloads(Pageable pageable) {
        Page<WorkloadDTO> workloads = workloadService.findAllWorkloads(pageable);
        return new ResponseEntity<>(workloads, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find Workload by ID", description = "Get a Workload by its ID")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(
                schema = @Schema(implementation = Workload.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Workload not found"
        )
    })
    public ResponseEntity<WorkloadDTO> getWorkloadById(@PathVariable Long id){
        Optional<WorkloadDTO> workloadDTO = workloadService.findWorkloadById(id);
        if (workloadDTO.isPresent()){
            return new ResponseEntity<>(workloadDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Insert Workload", description = "Insert a new Workload")
    @ApiResponses( value = {
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
    public ResponseEntity<WorkloadDTO> insertWorkload(@RequestBody WorkloadDTO workloadDTO){
        
        workloadDTO = workloadService.insertWorkload(workloadDTO).get();
        if (workloadDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(workloadDTO.getId()).toUri();
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
    public ResponseEntity<WorkloadDTO> updateWorkload(@PathVariable Long id, @RequestBody WorkloadDTO workloadDTO){
        workloadDTO = workloadService.updateWorkload(id, workloadDTO);
        return new ResponseEntity<>(workloadDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Disable Workload", description = "Disable an existing Workload")
    @ApiResponse(
        responseCode = "204",
        description = "Workload disabled"
    )
    public ResponseEntity<Void> disableWorkload(@PathVariable Long id){
        workloadService.disableWorkload(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}/enable")
    @Operation(summary = "Enable Workload", description = "Delete an existing Workload")
    @ApiResponse(
        responseCode = "204",
        description = "Workload Enabled"
    )
    public ResponseEntity<Void> enableWorkload(@PathVariable Long id){
        workloadService.enableWorkload(id);
        return ResponseEntity.noContent().build();
    }
}
