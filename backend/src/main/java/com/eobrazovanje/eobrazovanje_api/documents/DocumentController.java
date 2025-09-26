package com.eobrazovanje.eobrazovanje_api.documents;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eobrazovanje.eobrazovanje_api.documents.dto.DocumentDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping()
    public ResponseEntity<DocumentDto> upload(
            @RequestParam UUID userId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(documentService.uploadDocument(userId, file));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DocumentDto>> getUserDocuments(@PathVariable UUID userId) {
        return ResponseEntity.ok(documentService.getDocumentsForUser(userId));
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<Void> deleteDoc(@PathVariable UUID docId) {
        documentService.deleteDocument(docId);
        return ResponseEntity.noContent().build();
    }
}
