import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Exam } from './exam.service';

export interface ExamBooking {
  id: string;
  exam: {
    id: string;
    course: {
      id: string;
      name: string;
    };
    professor: {
      id: string;
      name: string;
      surename: string;
    };
  };
  student: {
    id: string;
    name: string;
    surename: string;
    indexNumber: string;
  };
  status: 'APPLIED' | 'CANCELED' | 'FINISHED';
  examMonth: number;
  examDate: string;
  test1?: number;
  test2?: number;
  attendance?: number;
  totalScore?: number;
  finalGrade?: number;
  createdAt?: string;
  updatedAt?: string;
}

export interface Grade {
  test1: number;
  test2: number;
  attendance: number;
}

@Injectable({ providedIn: 'root' })
export class ExamBookingService {
  private apiUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  getExamBookings(examId: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/bookings/exams/${examId}`);
  }

  getStudnetExamBookings(studentId: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/bookings/students/${studentId}`);
  }

  getSubjectExams(subjectId: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/exams/course/${subjectId}`);
  }
  book(examId: string, studentId: string): Observable<Exam> {
    return this.http.post<Exam>(`${this.apiUrl}/bookings`, { examId, studentId });
  }

  cancel(examId: string, studentId: string): Observable<Exam> {
    return this.http.post<Exam>(`${this.apiUrl}/bookings/${examId}/students/${studentId}`, {});
  }

  grade(grade: Grade, examId: string): Observable<Grade> {
    return this.http.put<Grade>(`${this.apiUrl}/bookings/${examId}/grade`, grade);
  }
}
