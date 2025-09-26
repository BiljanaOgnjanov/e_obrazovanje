package com.eobrazovanje.eobrazovanje_api.finance.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record FinancialCardDto(
    UUID id,
    UUID studentId,
    Double balance,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
