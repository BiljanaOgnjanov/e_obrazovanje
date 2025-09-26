package com.eobrazovanje.eobrazovanje_api.finance.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.eobrazovanje.eobrazovanje_api.finance.TransactionType;

public record TransactionDto(
    UUID id,
    Double amount,
    TransactionType type,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
