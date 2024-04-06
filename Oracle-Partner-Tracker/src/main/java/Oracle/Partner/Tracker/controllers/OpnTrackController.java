package Oracle.Partner.Tracker.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Oracle.Partner.Tracker.dto.OpnTrackDTO;
import Oracle.Partner.Tracker.entities.OpnTrack;
import Oracle.Partner.Tracker.services.OpnTrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/opntrack")
public class OpnTrackController {
    
    @Autowired
    private OpnTrackService opnTrackService;
    
    @GetMapping
    @Operation(summary = "OpnTrack", description = "Get all OpnTracks")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(
                array = @ArraySchema(
                    schema = @Schema(implementation = OpnTrack.class)
                )
            ),
            description = "OpnTracks retrieved"
        ),
        @ApiResponse(responseCode = "404", description = "OpnTracks not found")
    })
    public ResponseEntity<Page<OpnTrackDTO>> getAllOpnTracks(Pageable pageable) {
        Page<OpnTrackDTO> opnTracks = opnTrackService.findAllOpnTracks(pageable);
        return new ResponseEntity<>(opnTracks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find OpnTrack by ID", description = "Get an OpnTrack by its ID")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(
                schema = @Schema(implementation = OpnTrack.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "OpnTrack not found"
        )
    })
    public ResponseEntity<OpnTrackDTO> getOpnTrackById(@PathVariable String id){
        OpnTrackDTO opnTrackDTO = opnTrackService.findOpnTrackById(id);
        if (opnTrackDTO != null){
            return new ResponseEntity<>(opnTrackDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Insert OpnTrack", description = "Insert a new OpnTrack")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "201",
            description = "OpnTrack inserted",
            content = @Content(
                schema = @Schema(implementation = OpnTrackDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "OpnTrack already exists"
        )
    })
    public ResponseEntity<OpnTrackDTO> insertOpnTrack(@RequestBody OpnTrackDTO opnTrackDTO){
        Optional<OpnTrackDTO> optionalOpnTrack= Optional.ofNullable(opnTrackService.findOpnTrackByName(opnTrackDTO.getName()));
        if (optionalOpnTrack.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        opnTrackDTO = opnTrackService.insertOpnTrack(opnTrackDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(opnTrackDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(opnTrackDTO);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update OpnTrack", description = "Update an existing OpnTrack")
    @ApiResponse(
        responseCode = "200",
        description = "OpnTrack updated",
        content = @Content(
            schema = @Schema(implementation = OpnTrackDTO.class)
        )
    )
    public ResponseEntity<OpnTrackDTO> updateOpnTrack(@PathVariable String id, @RequestBody OpnTrackDTO opnTrackDTO){
        opnTrackDTO = opnTrackService.updateOpnTrack(id, opnTrackDTO);
        return new ResponseEntity<>(opnTrackDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Disable OpnTrack", description = "Disable an existing OpnTrack")
    @ApiResponse(
        responseCode = "204",
        description = "OpnTrack disabled"
    )
    public ResponseEntity<Void> disableOpnTrack(@PathVariable String id){
        opnTrackService.disableOpnTrack(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/enable")
    @Operation(summary = "Enable OpnTrack", description = "Enable an existing OpnTrack")
    @ApiResponse(
        responseCode = "204",
        description = "OpnTrack enabled"
    )
    public ResponseEntity<Void> enableOpnTrack(@PathVariable String id){
        opnTrackService.enableOpnTrack(id);
        return ResponseEntity.noContent().build();
    }
}
