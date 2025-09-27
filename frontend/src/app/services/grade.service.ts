import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GradeService {
  private apiUrl = 'http://localhost:3000/grades';

  constructor(private http: HttpClient) {}

  saveGrade(gradeData: any): Observable<any> {
    return this.http.post(this.apiUrl, gradeData);
  }
}
