import { Component, OnInit, inject } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ExamBookingService } from '../../services/exam-booking.service';
import { ExamService } from '../../services/exam.service';
import { ProfessorService } from '../../services/professor.service';
import { SubjectService } from '../../services/subject.service';
import { AuthService } from '../../services/auth.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-exam',
  imports: [DatePipe],
  templateUrl: './exam.component.html',
})
export class ExamComponent implements OnInit {
  private examBookingService = inject(ExamBookingService);
  private examService = inject(ExamService);
  private professorService = inject(ProfessorService);
  private subjectService = inject(SubjectService);
  private auth = inject(AuthService);

  user = this.auth.currentUser();
  bookings: any[] = [];

  view: 'current' | 'passed' | 'failed' | 'canceled' = 'current';

  async ngOnInit(): Promise<void> {
    await this.loadBookings();
  }

  async loadBookings(): Promise<void> {
    try {
      const rawBookings = await firstValueFrom(
        this.examBookingService.getStudnetExamBookings(this.user!.id)
      );

      this.bookings = await Promise.all(
        rawBookings.map(async (b: any) => {
          const exam = await firstValueFrom(this.examService.getById(b.examId));
          const [subject, professor] = await Promise.all([
            firstValueFrom(this.subjectService.getById(exam.courseId!)),
            firstValueFrom(this.professorService.getById(exam.professorId!)),
          ]);

          return {
            ...b,
            examDate: exam.examDate,
            examMonth: exam.examMonth,
            subject,
            professor,
          };
        })
      );
    } catch (err) {
      console.error('Greska pri ucitavanju ispita:', err);
    }
  }

  filteredBookings(): any[] {
    switch (this.view) {
      case 'passed':
        return this.bookings.filter((b) => b.status === 'PASSED');
      case 'failed':
        return this.bookings.filter((b) => b.status === 'FAILED');
      case 'canceled':
        return this.bookings.filter((b) => b.status === 'CANCELLED');
      default:
        return this.bookings.filter((b) => b.status === 'BOOKED');
    }
  }

  showGrade(booking?: any): boolean {
    return !!booking && (booking.status === 'PASSED' || booking.status === 'FAILED');
  }

  getStatusLabel(status: string): string {
    switch (status) {
      case 'PASSED':
        return 'Položen';
      case 'FAILED':
        return 'Nepoložen';
      case 'CANCELLED':
        return 'Otkazan';
      case 'BOOKED':
        return 'Prijavljen';
      default:
        return '-';
    }
  }

  setView(view: typeof this.view) {
    this.view = view;
  }

  getTableTitle(): string {
    return {
      current: 'Aktivni ispiti',
      passed: 'Položeni ispiti',
      failed: 'Nepoloženi ispiti',
      canceled: 'Otkazani ispiti',
    }[this.view];
  }
}
