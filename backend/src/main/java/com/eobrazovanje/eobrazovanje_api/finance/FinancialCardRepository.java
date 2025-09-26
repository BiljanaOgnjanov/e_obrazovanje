package com.eobrazovanje.eobrazovanje_api.finance;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialCardRepository extends JpaRepository<FinancialCard, UUID> {
    Optional<FinancialCard> findByStudentId(UUID studentId);
}
