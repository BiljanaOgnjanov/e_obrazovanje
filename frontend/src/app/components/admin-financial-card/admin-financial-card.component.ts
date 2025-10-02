import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FinancialCardService, Transactions } from '../../services/financial-card.service';
import { Student, StudentService } from '../../services/student.service';

@Component({
  selector: 'app-financial-card-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-financial-card.component.html',
})
export class AdminFinancialCardComponent implements OnInit {
  private financialCardService = inject(FinancialCardService);
  private studentService = inject(StudentService);

  students: Student[] = [];
  selectedStudentId: string | null = null;
  transactions: Transactions[] = [];

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.studentService.getAll().subscribe({
      next: (data) => (this.students = data),
      error: (err) => console.error('Greska pri ucitavanju studenata:', err),
    });
  }

  onSelectStudent(studentId: string) {
    if (!studentId) {
      this.transactions = [];
      return;
    }

    this.financialCardService.getTransactions(studentId).subscribe({
      next: (data) => (this.transactions = data),
      error: (err) => console.error('Greska pri ucitavanju transakcija:', err),
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
