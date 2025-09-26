package com.eobrazovanje.eobrazovanje_api.finance;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.eobrazovanje.eobrazovanje_api.exceptions.ResourceNotFoundException;
import com.eobrazovanje.eobrazovanje_api.finance.dto.FinancialCardDto;
import com.eobrazovanje.eobrazovanje_api.finance.dto.TransactionDto;
import com.eobrazovanje.eobrazovanje_api.studentProfiles.StudentProfileRepository;

@Service
public class FinancialCardService {
    private final FinancialCardRepository financialCardRepository;
    private final TransactionRepository transactionRepository;
    private final StudentProfileRepository studentProfileRepository;

    public FinancialCardService(
        FinancialCardRepository financialCardRepository,
        TransactionRepository transactionRepository,
        StudentProfileRepository studentProfileRepository
    ) {
        this.financialCardRepository = financialCardRepository;
        this.transactionRepository = transactionRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public FinancialCardDto getFinancialCard (UUID studentId) {
        FinancialCard financialCard = financialCardRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial card not found."));

        return toFinancialCardDto(financialCard);
    }

    public List<TransactionDto> getTransactions (UUID studentId) {
        FinancialCard card = financialCardRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial card not found."));

        return transactionRepository.findByCardIdOrderByCreatedAtDesc(card.getId()).stream()
            .map(this::toTransactionDto)
            .toList();
    }

    public void deposit (UUID studentId, double amount, TransactionType type) {
        FinancialCard financialCard = financialCardRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial card not found."));
        
        financialCard.setBalance(financialCard.getBalance() + amount);

        financialCardRepository.save(financialCard);

        Transaction tx = Transaction.builder()
            .card(financialCard)
            .amount(amount)
            .type(type)
            .build();

        transactionRepository.save(tx);
    }

    public void withdraw (UUID studentId, double amount, TransactionType type) {
        FinancialCard financialCard = financialCardRepository.findByStudentId(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Financial card not found."));

        if (financialCard.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds.");
        }

        financialCard.setBalance(financialCard.getBalance() - amount);

        financialCardRepository.save(financialCard);

        Transaction tx = Transaction.builder()
            .card(financialCard)
            .amount(-amount)
            .type(type)
            .build();

        transactionRepository.save(tx);
    }

    private FinancialCardDto toFinancialCardDto (FinancialCard card) {
        return new FinancialCardDto(
            card.getId(),
            card.getStudent().getId(),
            card.getBalance(),
            card.getCreatedAt(),
            card.getUpdatedAt()
        );
    }

    private TransactionDto toTransactionDto (Transaction tx) {
        return new TransactionDto(
            tx.getId(),
            tx.getAmount(),
            tx.getType(),
            tx.getCreatedAt(),
            tx.getUpdatedAt()
        );
    }
}
