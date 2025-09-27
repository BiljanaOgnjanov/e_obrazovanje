import { Component, inject, OnInit } from '@angular/core';
import { FinancialCardService, Transactions } from '../../services/financial-card.service';
import { AuthService } from '../../services/auth.service';
import { CurrencyPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-financial-card',
  imports: [DatePipe, CurrencyPipe],
  templateUrl: './financial-card.component.html',
})
export class FinancialCardComponent implements OnInit {
  private auth = inject(AuthService);
  user = this.auth.currentUser();
  studentId = this.user?.id!;
  transactions: Transactions[] = [];

  constructor(private financialCardService: FinancialCardService) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.financialCardService.getTransactions(this.studentId).subscribe({
      next: (data) => {
        this.transactions = data;
      },
      error: (err) => {
        console.error('Error fetching transactions', err);
      },
    });
  }

  getBadgeClass(type: string): string {
    switch (type) {
      case 'DEPOSIT':
        return 'bg-success';
      case 'EXAM_FEE':
        return 'bg-danger';
      case 'REFUND':
        return 'bg-warning text-dark';
      default:
        return 'bg-secondary';
    }
  }

  getTypeLabel(type: string): string {
    switch (type) {
      case 'DEPOSIT':
        return 'Uplata';
      case 'EXAM_FEE':
        return 'Naknada za ispit';
      case 'REFUND':
        return 'Povraćaj sredstava';
      default:
        return type;
    }
  }
}
