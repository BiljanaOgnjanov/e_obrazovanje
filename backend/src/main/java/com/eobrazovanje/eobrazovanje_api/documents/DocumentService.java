package com.eobrazovanje.eobrazovanje_api.documents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eobrazovanje.eobrazovanje_api.documents.dto.DocumentDto;
import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.users.User;
import com.eobrazovanje.eobrazovanje_api.users.UserRepository;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    public DocumentService(
        DocumentRepository documentRepository,
        UserRepository userRepository
    ) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public DocumentDto uploadDocument(UUID userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (file.isEmpty()) {
            throw new IllegalStateException("File cannot be empty");
        }

        List<String> allowedTypes = List.of("application/pdf", "image/png", "image/jpeg");

        if (!allowedTypes.contains(file.getContentType())) {
            throw new IllegalStateException("Invalid file type: " + file.getContentType());
        }

        long maxSize = 15 * 1024 * 1024; // 15 MB

        if (file.getSize() > maxSize) {
            throw new IllegalStateException("File size exceeds 15MB limit");
        }

        String uploadDir = "uploads/" + userId;
        Files.createDirectories(Paths.get(uploadDir));

        String filePath = uploadDir + "/" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Document doc = Document.builder()
                .user(user)
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath)
                .build();

        return toDto(documentRepository.save(doc));
    }

    public List<DocumentDto> getDocumentsForUser(UUID userId) {
        return documentRepository.findByUserId(userId)
            .stream()
            .map(this::toDto)
            .toList();
    }

    public void deleteDocument(UUID docId) {
        if (!documentRepository.existsById(docId)) {
            throw new ResourceNotFoundException("Document not found.");
        }

        Document doc = documentRepository.findById(docId)
            .orElseThrow(() -> new ResourceNotFoundException("Document not found."));

        try {
            Path path = Paths.get(doc.getFilePath());
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + doc.getFilePath());
        }

        documentRepository.deleteById(docId);
    }

    private DocumentDto toDto(Document document) {
        return new DocumentDto(
            document.getId(),
            document.getUser().getId(),
            document.getFileName(),
            document.getFileType(),
            document.getFilePath(),
            document.getCreatedAt(),
            document.getUpdatedAt()
        );
    }
}
