package com.eventspace.spring.spaceservice.controller;

import com.eventspace.spring.spaceservice.dto.SpaceRequest;
import com.eventspace.spring.spaceservice.model.entity.Space;
import com.eventspace.spring.spaceservice.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/space")
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

    @PostMapping
    public ResponseEntity<Space> createSpace(@RequestBody SpaceRequest spaceRequest) {
        return ResponseEntity.ok(spaceService.addSpace(spaceRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Space> updateSpace(@RequestBody SpaceRequest spaceRequest, @PathVariable Long id) {
        return ResponseEntity.ok(spaceService.updateSpace(id, spaceRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.ok("Space with id: " + id + " was deleted");
    }
}
