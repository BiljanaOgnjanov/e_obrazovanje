import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Professor } from './professor.service';
import { Student } from './student.service';

export interface Subject {
  id: string;
  name: string;
  year: number;
  espb: number;
}

@Injectable({ providedIn: 'root' })
export class SubjectService {
  private apiUrl = `${environment.apiUrl}/courses`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.apiUrl);
  }

  create(subject: Subject): Observable<Subject> {
    return this.http.post<Subject>(this.apiUrl, subject);
  }

  update(id: number, subject: Subject): Observable<Subject> {
    return this.http.put<Subject>(`${this.apiUrl}/${id}`, subject);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getById(subjectId: string): Observable<Subject> {
    return this.http.get<Subject>(`${this.apiUrl}/students/${subjectId}`, {});
  }

  getSubjectStudents(subjectId: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/${subjectId}/students`, {});
  }

  getSubjectProfessors(subjectId: string): Observable<Professor[]> {
    return this.http.get<Professor[]>(`${this.apiUrl}/${subjectId}/professors`, {});
  }

  getProfessorSubjects(subjectId: string): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.apiUrl}/${subjectId}/professors`, {});
  }

  assignStudent(data: { courseId: string; studentId: string }): Observable<Subject> {
    return this.http.post<Subject>(`${this.apiUrl}/assign-student`, data);
  }

  assignProfessor(data: { courseId: string; professorId: string }): Observable<Subject> {
    return this.http.post<Subject>(`${this.apiUrl}/assign-professor`, data);
  }

  deleteStudent(data: { courseId: string; studentId: string }): Observable<Subject> {
    return this.http.delete<any>(`${this.apiUrl}/${data.courseId}/students/${data.studentId}`, {});
  }

  deleteProfessor(data: { courseId: string; professorId: string }): Observable<Subject> {
    return this.http.delete<any>(
      `${this.apiUrl}/${data.courseId}/professors/${data.professorId}`,
      {}
    );
  }
}
