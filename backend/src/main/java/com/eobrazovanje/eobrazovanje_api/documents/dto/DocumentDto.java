package com.eobrazovanje.eobrazovanje_api.documents.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentDto(
    UUID id,
    UUID userId,
    String fileName,
    String fileType,
    String filePath,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
