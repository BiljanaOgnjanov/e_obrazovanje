package com.eobrazovanje.eobrazovanje_api.finance;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eobrazovanje.eobrazovanje_api.finance.dto.FinancialCardDto;
import com.eobrazovanje.eobrazovanje_api.finance.dto.TransactionDto;

@RestController
@RequestMapping("/students/{studentId}/card")
public class FinancialCardController {
    @Autowired
    private FinancialCardService financialCardService;

    @GetMapping
    public ResponseEntity<FinancialCardDto> getCard(@PathVariable UUID studentId) {
        return ResponseEntity.ok(financialCardService.getFinancialCard(studentId));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable UUID studentId) {
        return ResponseEntity.ok(financialCardService.getTransactions(studentId));
    }
}
