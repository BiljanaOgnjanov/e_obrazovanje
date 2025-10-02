import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Professor, ProfessorService } from '../../services/professor.service';
import { Exam, ExamService } from '../../services/exam.service';

@Component({
  selector: 'app-exam-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [DatePipe],
  templateUrl: './exam-management.html',
})
export class ExamManagementComponent implements OnInit {
  private professorService = inject(ProfessorService);
  private examService = inject(ExamService);
  private datePipe = inject(DatePipe);

  professors: Professor[] = [];
  selectedProfessorId: string | null = null;
  exams: Exam[] = [];

  editingExam: Exam | null = null;

  ngOnInit() {
    this.loadProfessors();
  }

  loadProfessors() {
    this.professorService.getAllProfessors().subscribe({
      next: (data) => (this.professors = data),
      error: (err) => console.error('Greska pri ucitavanju profesora:', err),
    });
  }

  onSelectProfessor(professorId: string) {
    this.exams = [];
    this.examService.getProfessorExams(professorId).subscribe({
      next: (data) => (this.exams = data),
      error: (err) => console.error('Greska pri ucitavanju ispita:', err),
    });
  }

  startEdit(exam: Exam) {
    this.editingExam = { ...exam };
  }

  cancelEdit() {
    this.editingExam = null;
  }

  saveEdit() {
    if (!this.editingExam) return;

    const examDateLocale = this.datePipe.transform(
      this.editingExam.examDate,
      'yyyy-MM-ddTHH:mm:ss'
    );

    const payload = {
      examDate: examDateLocale!,
      examMonth: this.editingExam.examMonth,
    };

    this.examService.update(this.editingExam.id, payload).subscribe({
      next: () => {
        const index = this.exams.findIndex((e) => e.id === this.editingExam!.id);
        if (index > -1) this.exams[index] = { ...this.editingExam! };

        this.cancelEdit();
        alert('Ispit uspesno azuriran!');
      },
      error: () => {
        alert('Greška pri azuriranju ispita');
      },
    });
  }
}
