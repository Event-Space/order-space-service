package com.eventspace.spring.spaceservice.controller;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Space> createSpace(@RequestPart("spaceRequest") SpaceRequest spaceRequest,
                                             @RequestPart(name = "file") MultipartFile file) {
        return ResponseEntity.ok(spaceService.addSpace(spaceRequest, file));
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Space> updateSpace(@RequestBody SpaceRequest spaceRequest, @PathVariable Long id, @RequestPart(name = "file") MultipartFile file) {
        return ResponseEntity.ok(spaceService.updateSpace(id, spaceRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.ok("Space with id: " + id + " was deleted");
    }
}
