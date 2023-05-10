package com.example.bookyy.Controllers;

import com.example.bookyy.Entites.Media;
import com.example.bookyy.ServicesImpl.Mediaservice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/media")
@CrossOrigin("*")
public class MediaController {
    final Mediaservice service;

    public MediaController(Mediaservice service) {
        this.service = service;
    }

    @PostMapping("/upload/db")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
        Media doc = new Media();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setDocName(fileName);
        try {
            doc.setFile(file.getBytes());
            doc.setType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName).path("/db")
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
    @GetMapping("/download/{fileName:.+}/db")
    public ResponseEntity downloadFromDB(@PathVariable String fileName) {
        Media document = service.findByDocName(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(document.getFile());
    }
    @GetMapping("/download/{id}")
    public ResponseEntity findbybook(@PathVariable int id) {
        Media document = service.findByBookid(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getDocName() + "\"")
                .body(document.getFile());
    }

}
