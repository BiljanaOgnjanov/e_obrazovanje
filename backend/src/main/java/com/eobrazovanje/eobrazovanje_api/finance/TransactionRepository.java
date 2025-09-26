package com.eobrazovanje.eobrazovanje_api.finance;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByCardIdOrderByCreatedAtDesc(UUID cardId);
}
