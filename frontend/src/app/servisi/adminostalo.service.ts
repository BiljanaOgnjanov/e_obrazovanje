import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Student } from '../modeli/student';
import { Nastavnik } from '../modeli/nastavnik';

@Injectable({
  providedIn: 'root'
})
export class AdminostaloService {

  adminUrl = 'http://localhost:8080/admin'

  constructor(private http: HttpClient) { }

   // Fetch all students
   getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.adminUrl}/students`);
  }

  // Fetch all professors
  getAllProfessors(): Observable<Nastavnik[]> {
    return this.http.get<Nastavnik[]>(`${this.adminUrl}/professors`);
  }

  // Add a new student
  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.adminUrl}/add-student`, student);
  }

  // Add a new professor
  addProfessor(professor: Nastavnik): Observable<Nastavnik> {
    return this.http.post<Nastavnik>(`${this.adminUrl}/add-professor`, professor);
  }

  // Update a user (Student or Professor)
  updateUser(user: Student | Nastavnik): Observable<any> {
    return this.http.put<any>(`${this.adminUrl}/update`, user);
  }

  // Delete a user by username
  deleteUser(username: string): Observable<string> {
    return this.http.delete<string>(`${this.adminUrl}/delete/${username}`);
  }

  // Method to reset overenSemestar to "neoveren" for all students
  resetOverenSemestar(): Observable<string> {
    return this.http.put<string>(`${this.adminUrl}/resetOverenSemestar`, null);
  }
}
