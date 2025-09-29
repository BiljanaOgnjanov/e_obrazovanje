import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Subject } from './subject.service';

export interface ProfessorRole {
  role: 'TEACHER' | 'ASSISTENT' | 'DEMONSTRATOR';
}

export interface Professor {
  id: string;
  fristname: string;
  lastname: string;
  role: ProfessorRole;
  passowrd: number;
  courses?: Subject[];
}

@Injectable({ providedIn: 'root' })
export class ProfessorService {
  private apiUrl = `${environment.apiUrl}/professors`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Professor[]> {
    return this.http.get<Professor[]>(this.apiUrl);
  }

  create(professor: Professor): Observable<Professor> {
    return this.http.post<Professor>(this.apiUrl, professor);
  }

  update(id: string, professor: Professor): Observable<Professor> {
    return this.http.put<Professor>(`${this.apiUrl}/${id}`, professor);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getById(professorId: string): Observable<Professor> {
    return this.http.get<Professor>(`${this.apiUrl}/${professorId}`, {});
  }

  getProfessorSubjects(professorId: string): Observable<Subject[]> {
    return this.http.get<Subject[]>(`${this.apiUrl}/${professorId}/courses`, {});
  }
}
