package ru.netology.cloud_service.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloud_service.model.dtos.request.FileNameEditRequest;
import ru.netology.cloud_service.model.dtos.response.ResponseForGetAllFiles;
import ru.netology.cloud_service.service.StorageFileService;

import java.util.List;

@RestController
@AllArgsConstructor
public class StorageFileController {
    private StorageFileService storageFileService;

    @PostMapping("/file")
    public ResponseEntity<?> fileUpload(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename, MultipartFile file
    ) {
        storageFileService.fileUpload(authToken, filename, file);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> fileDelete(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename
    ) {
        System.out.println(authToken);
        storageFileService.fileDelete(authToken, filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> fileDownload(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename
    ) {
        byte[] file = storageFileService.fileDownload(authToken, filename);
        return ResponseEntity.ok()
                .header("Content-Type", "multipart/form-data")
                .body(new ByteArrayResource(file));
    }

    @PutMapping("/file")
    public ResponseEntity<?> fileNameEdit(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("filename") String filename,
            @RequestBody FileNameEditRequest fileNameEditRequest
    ) {
        storageFileService.fileNameEdit(authToken, filename, fileNameEditRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<ResponseForGetAllFiles> getAllFiles(
            @RequestHeader("auth-token") String authToken,
            @RequestParam("limit") Integer limit) {
        return storageFileService.getAllFiles(authToken, limit);
    }
}
