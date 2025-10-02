import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Exam, ExamService } from '../../services/exam.service';
import { ProfessorService } from '../../services/professor.service';

interface NewExam {
  id: string;
  courseId: string;
  professorId: string;
  examMonth: number;
  examDate: string;
}

interface Subject {
  id: string;
  name: string;
  exams: Exam[];
}

@Component({
  selector: 'app-schedule',
  standalone: true,
  imports: [CommonModule, FormsModule],
  providers: [DatePipe],
  templateUrl: './schedule.component.html',
})
export class ScheduleComponent implements OnInit {
  private examService = inject(ExamService);
  private professorService = inject(ProfessorService);
  private auth = inject(AuthService);
  private datePipe = inject(DatePipe);

  user = this.auth.currentUser();

  months = [
    { value: 1, label: 'Januar' },
    { value: 2, label: 'Februar' },
    { value: 3, label: 'Mart' },
    { value: 4, label: 'April' },
    { value: 5, label: 'Maj' },
    { value: 6, label: 'Jun' },
    { value: 7, label: 'Jul' },
    { value: 8, label: 'Avgust' },
    { value: 9, label: 'Septembar' },
    { value: 10, label: 'Oktobar' },
    { value: 11, label: 'Novembar' },
    { value: 12, label: 'Decembar' },
  ];

  subjects: Subject[] = [];
  selectedSubjectId: string | null = null;

  newExam: NewExam = {
    id: '',
    courseId: '',
    professorId: this.user?.id!,
    examMonth: 0,
    examDate: '',
  };

  ngOnInit(): void {
    if (this.user?.userType === 'PROFESSOR') {
      this.loadSubjects();
    }
  }

  loadSubjects() {
    this.professorService.getProfessorSubjects(this.user!.id).subscribe({
      next: (res: any[]) => {
        this.subjects = res.map((s) => ({ ...s, exams: [] }));
        this.loadExams();
      },
      error: (err) => console.error('Greska pri ucitavanju predmeta:', err),
    });
  }

  loadExams() {
    this.examService.getProfessorExams(this.user!.id).subscribe({
      next: (res: Exam[]) => {
        this.subjects.forEach((subj) => {
          subj.exams = res.filter((exam) => exam.courseId === subj.id);
        });
      },
      error: (err) => console.error('Greska pri ucitavanju profesorovih ispita:', err),
    });
  }

  get selectedSubject(): Subject | null {
    return this.subjects.find((s) => s.id === this.selectedSubjectId) || null;
  }

  addExam() {
    if (!this.selectedSubject) return;

    const examDateLocale = this.datePipe.transform(this.newExam.examDate, 'yyyy-MM-ddTHH:mm:ss');
    const exam: NewExam = {
      ...this.newExam,
      courseId: this.selectedSubject.id,
      examDate: examDateLocale!,
    };

    this.examService.create(exam).subscribe({
      next: (created: Exam) => {
        this.selectedSubject!.exams.push(created);
        this.newExam = {
          id: '',
          courseId: '',
          professorId: this.user?.id!,
          examMonth: 0,
          examDate: '',
        };
      },
      error: () => {
        alert('Greška pri zakazivanju ispita');
      },
    });
  }

  removeExam(examId: string) {
    if (!this.selectedSubject) return;
    this.examService.delete(examId).subscribe({
      next: () => {
        this.selectedSubject!.exams = this.selectedSubject!.exams.filter((e) => e.id !== examId);
      },
      error: () => {
        alert('Greška pri brisanju ispita');
      },
    });
  }

  getMonthMinDate(month: number | null): string {
    if (!month) return '';
    const now = new Date();
    return new Date(now.getFullYear(), month - 1, 1).toISOString().split('T')[0];
  }

  getMonthMaxDate(month: number | null): string {
    if (!month) return '';
    const now = new Date();
    return new Date(now.getFullYear(), month, 0).toISOString().split('T')[0];
  }
}
