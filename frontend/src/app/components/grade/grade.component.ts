import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { ExamService } from '../../services/exam.service';
import { ProfessorService } from '../../services/professor.service';
import { ExamBookingService } from '../../services/exam-booking.service';
import { StudentService } from '../../services/student.service';
import { firstValueFrom } from 'rxjs';

interface Student {
  id: string;
  firstName: string;
  lastName: string;
  test1?: number;
  test2?: number;
  attendance?: number;
  finalScore?: number;
  finalGrade?: number;
  bookingId?: string;
}

interface Exam {
  id: string;
  examDate: string;
  examMonth: number;
  students: Student[];
}

interface Subject {
  id: string;
  name: string;
  exams: Exam[];
}

@Component({
  selector: 'app-grade',
  imports: [CommonModule, FormsModule],
  templateUrl: './grade.component.html',
  styleUrls: ['./grade.component.css'],
})
export class GradeComponent implements OnInit {
  private examService = inject(ExamService);
  private examBookingSerive = inject(ExamBookingService);
  private professorService = inject(ProfessorService);
  private studentService = inject(StudentService);
  private auth = inject(AuthService);

  user = this.auth.currentUser();

  subjects: Subject[] = [];
  selectedSubjectId: string | null = null;
  selectedExamId: string | null = null;

  ngOnInit() {
    this.loadSubjects();
  }

  loadSubjects() {
    if (!this.user) return;
    this.professorService.getProfessorSubjects(this.user.id).subscribe({
      next: (subjects: any) => {
        this.subjects = subjects;
      },
      error: (err) => console.error('Neuspesno dohvatanje predmeta:', err),
    });
  }

  get selectedSubject(): Subject | null {
    return this.subjects.find((s) => s.id === this.selectedSubjectId) || null;
  }

  get selectedExam(): Exam | null {
    return this.selectedSubject?.exams.find((e) => e.id === this.selectedExamId) || null;
  }

  onSelectSubject(subjectId: string) {
    this.selectedExamId = null;
    this.examService.getProfessorExams(this.user!.id).subscribe({
      next: (exams) => {
        const filtered = exams.filter((e) => e.courseId === subjectId);
        const subj = this.subjects.find((s) => s.id === subjectId) as any;
        if (subj) subj.exams = filtered;
      },
      error: (err) => console.error('Neuspesno dohvatanje ispita:', err),
    });
  }

  onSelectExam(examId: string) {
    this.selectedExamId = examId;
    if (!this.selectedExam) return;

    this.examBookingSerive.getExamBookings(examId).subscribe({
      next: async (bookings: any[]) => {
        const students: Student[] = [];

        for (const booking of bookings) {
          if (booking.status !== 'BOOKED') continue;

          try {
            const student = await firstValueFrom(
              this.studentService.getStudentById(booking.studentId)
            );

            if (student) {
              students.push({
                id: student.id,
                bookingId: booking.id,
                firstName: student.firstName,
                lastName: student.lastName,
                test1: booking.test1 || undefined,
                test2: booking.test2 || undefined,
                attendance: booking.attendance || undefined,
                finalScore: booking.totalScore || undefined,
                finalGrade: booking.grade || undefined,
              });
            }
          } catch (err) {
            console.error('Neuspesno dohvatanje studentskih detalja:', err);
          }
        }

        if (this.selectedExam) this.selectedExam.students = students;
      },
      error: (err) => console.error('Nuspesno dohvatanje prijavljenih ispita:', err),
    });
  }

  calculateFinal(student: Student) {
    const test1 = student.test1 || 0;
    const test2 = student.test2 || 0;
    const attendance = student.attendance || 0;

    student.finalScore = test1 + test2 + attendance;

    switch (true) {
      case student.finalScore >= 91:
        student.finalGrade = 10;
        break;
      case student.finalScore >= 81:
        student.finalGrade = 9;
        break;
      case student.finalScore >= 71:
        student.finalGrade = 8;
        break;
      case student.finalScore >= 61:
        student.finalGrade = 7;
        break;
      case student.finalScore >= 51:
        student.finalGrade = 6;
        break;
      default:
        student.finalGrade = 5;
    }
  }

  saveGrade(student: Student) {
    if (
      !this.selectedSubject ||
      !this.selectedExam ||
      (student.test1 && student.test1 < 1) ||
      (student.test2 && student.test2 < 1)
    )
      return;

    const gradeData = {
      test1: student.test1!,
      test2: student.test2!,
      attendance: student.attendance || 0,
    };

    this.examBookingSerive.grade(gradeData, student.bookingId!).subscribe({
      next: () => {
        alert(
          `Ocena za ${student.firstName} ${student.lastName} (${this.selectedSubject?.name}) sačuvana!`
        );
      },
      error: (err) => {
        console.error('Greska pri cuvanju ocene:', err);
        alert('Greška pri čuvanju ocene');
      },
    });
  }
}
