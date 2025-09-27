import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Professor } from './professor.service';
import { Subject } from './subject.service';

export interface Exam {
  id: string;
  course: Subject;
  professor: Professor;
  status: 'BOOKED' | 'CANCELLED' | 'PASSED' | 'FAILED';
  examMonth: number;
  examDate: string;
  balance?: number;
  courseId?: string;
  professorId?: string;
  createdAt?: string;
  updatedAt?: string;
}

@Injectable({ providedIn: 'root' })
export class ExamService {
  private apiUrl = `${environment.apiUrl}/exams`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Exam[]> {
    return this.http.get<Exam[]>(this.apiUrl);
  }

  getProfessorExams(professorId: string): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.apiUrl}/professor/${professorId}`);
  }

  create(exam: Partial<Exam>): Observable<Exam> {
    return this.http.post<Exam>(this.apiUrl, exam);
  }

  delete(examId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${examId}`);
  }
}
