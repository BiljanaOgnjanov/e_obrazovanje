import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Predmet } from '../modeli/predmet';
@Injectable({
  providedIn: 'root'
})
export class AdminpredmetiService {

  url='http://localhost:8080/admin';
  constructor(private http: HttpClient) { }

  getAllCourses(): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${this.url}/predmeti`);
  }

  createCourse(predmet: Predmet): Observable<Predmet> {
    return this.http.post<Predmet>(`${this.url}/predmeti`, predmet);
  }

  deleteCourse(id: number): Observable<void> {
    console.log(`Deleting course with ID: ${id}`);

  // Check if id is a number and not NaN
  if (typeof id !== 'number' || isNaN(id)) {
    console.error('Invalid ID passed to deleteCourse:', id);
    throw new Error('Invalid ID');
  }

    return this.http.delete<void>(`${this.url}/predmeti/${id}`);
  }

}
