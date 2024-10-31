package com.eventspace.spring.spaceservice.controller;


import com.eventspace.spring.spaceservice.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
@CrossOrigin("*")
public class FileController {

    private final StorageService storageService;

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        try {
            String contentType = Files.probeContentType(file.getFile().toPath());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
        } catch (IOException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
