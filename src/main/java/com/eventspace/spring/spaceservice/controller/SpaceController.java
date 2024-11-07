package com.eventspace.spring.spaceservice.controller;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.model.entity.Slot;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.service.SpaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/space")
@CrossOrigin("*")
public class SpaceController {
    private final SpaceService spaceService;

    @GetMapping
    public ResponseEntity<List<Space>> getAllSpaces() {
        return ResponseEntity.ok(spaceService.getAllSpace());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Space> getSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.getSpace(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Space> createSpace(
            @RequestPart("spaceRequest") String spaceRequestJson,
            @RequestPart("file") MultipartFile file) throws JsonProcessingException {

        SpaceRequest spaceRequest = new ObjectMapper().readValue(spaceRequestJson, SpaceRequest.class);

        return ResponseEntity.ok(spaceService.addSpace(spaceRequest, file));
    }


    @PutMapping(value = "/updateWithFile/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Space> updateSpaceWithFile(@PathVariable Long id, @RequestPart("spaceRequest") String spaceRequestJson,
                                             @RequestPart(value = "file") MultipartFile file) throws Exception {

        SpaceRequest spaceRequest = new ObjectMapper().readValue(spaceRequestJson, SpaceRequest.class);

        return ResponseEntity.ok(spaceService.updateSpace(id, spaceRequest, file));
    }
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Space> updateSpace(@PathVariable Long id, @RequestPart("spaceRequest") String spaceRequestJson) throws Exception {

        SpaceRequest spaceRequest = new ObjectMapper().readValue(spaceRequestJson, SpaceRequest.class);

        return ResponseEntity.ok(spaceService.updateSpace(id, spaceRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.ok("Space with id: " + id + " was deleted");
    }

    @GetMapping("/{spaceId}/free-times")
    public List<Slot> getFreeTimes(
            @PathVariable Long spaceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return spaceService.getFreeTimes(spaceId, date);
    }
}
