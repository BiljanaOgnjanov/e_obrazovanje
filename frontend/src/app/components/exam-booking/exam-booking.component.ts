import { Component, OnInit, inject } from '@angular/core';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ExamBookingService } from '../../services/exam-booking.service';
import { ExamService } from '../../services/exam.service';
import { ProfessorService } from '../../services/professor.service';
import { SubjectService } from '../../services/subject.service';
import { AuthService } from '../../services/auth.service';
import { StudentService } from '../../services/student.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-exam-booking',
  imports: [DatePipe, FormsModule],
  templateUrl: './exam-booking.component.html',
})
export class ExamBookingComponent implements OnInit {
  private examBookingService = inject(ExamBookingService);
  private examService = inject(ExamService);
  private professorService = inject(ProfessorService);
  private subjectService = inject(SubjectService);
  private studentService = inject(StudentService);
  private auth = inject(AuthService);

  user = this.auth.currentUser();
  balance: number = 0;

  bookings: any[] = [];
  subjects: any[] = [];
  selectedSubjectId: string | null = null;
  subjectExams: any[] = [];

  async ngOnInit(): Promise<void> {
    this.loadBalance();
    await this.loadBookings();
    await this.loadSubjects();
  }

  loadBalance() {
    this.studentService.getCard(this.user!.id).subscribe({
      next: (card) => (this.balance = card.balance),
      error: (err) => console.error('Greska pri ucitavanju kartice:', err),
    });
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

  async loadSubjects(): Promise<void> {
    try {
      this.subjects = await firstValueFrom(this.studentService.getStudentSubjects(this.user!.id));
    } catch (err) {
      console.error('Greska pri ucitavanju predmeta:', err);
    }
  }

  async onSelectSubject(subjectId: string | null): Promise<void> {
    this.selectedSubjectId = subjectId;
    if (!subjectId) {
      this.subjectExams = [];
      return;
    }
    try {
      this.subjectExams = await firstValueFrom(this.examService.getSubjectExams(subjectId));
    } catch (err) {
      console.error('Greska pri ucitavanju ispita odabranog predmeta:', err);
    }
  }

  async bookExam(examId: string): Promise<void> {
    try {
      await firstValueFrom(this.examBookingService.book(examId, this.user!.id));
      await this.loadBookings();
      this.loadBalance();
      if (this.selectedSubjectId) {
        await this.onSelectSubject(this.selectedSubjectId);
      }
    } catch (err) {
      console.error('Greska pri prijavi ispita:', err);
    }
  }

  async withdrawExam(bookingId: string): Promise<void> {
    try {
      await firstValueFrom(this.examBookingService.cancel(bookingId, this.user!.id));
      await this.loadBookings();
      this.loadBalance();
      if (this.selectedSubjectId) {
        await this.onSelectSubject(this.selectedSubjectId);
      }
    } catch (err) {
      console.error('Greska pri brisanju ispita:', err);
    }
  }

  upcomingBookings(): any[] {
    const now = new Date();
    return this.bookings.filter((b) => b.status === 'BOOKED' && new Date(b.examDate) > now);
  }

  showGrade(booking: any): boolean {
    return booking.status === 'PASSED' || booking.status === 'FAILED';
  }

  getStatusLabel(status: string): string {
    switch (status) {
      case 'PASSED':
        return 'Polozen';
      case 'FAILED':
        return 'Nepolozen';
      case 'CANCELED':
        return 'Otkazan';
      case 'BOOKED':
        return 'Prijavljen';
      default:
        return '-';
    }
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'PASSED':
        return 'bg-success';
      case 'FAILED':
        return 'bg-danger';
      case 'CANCELED':
        return 'bg-warning';
      case 'BOOKED':
        return 'bg-primary';
      default:
        return 'bg-secondary';
    }
  }
}
