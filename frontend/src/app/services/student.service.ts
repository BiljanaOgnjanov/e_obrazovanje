import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Subject } from './subject.service';

export interface Student {
  id: string;
  firstName: string;
  lastName: string;
  indexNumber: string;
  grade?: number;
  courses?: Subject[];
}

export interface Card {
  id: string;
  studentId: string;
  balance: number;
}

@Injectable({ providedIn: 'root' })
export class StudentService {
  private apiUrl = `${environment.apiUrl}/students`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  create(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  update(id: number, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${id}`, student);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getStudentSubjects(subjectId: string): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.apiUrl}/${subjectId}/courses`, {});
  }

  getStudentById(subjectId: string): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${subjectId}`, {});
  }

  getCard(subjectId: string): Observable<Card> {
    return this.http.get<Card>(`${this.apiUrl}/${subjectId}/card`, {});
  }
}
