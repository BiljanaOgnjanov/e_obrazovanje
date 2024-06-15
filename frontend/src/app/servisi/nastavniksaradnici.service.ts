import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Nastavnik } from '../modeli/nastavnik';
import { Predmet } from '../modeli/predmet';

@Injectable({
  providedIn: 'root'
})
export class NastavniksaradniciService {
  private baseUrl = 'http://localhost:8080/profesori';

  constructor(private http: HttpClient) {}

  getCoursesByProfessor(username: string): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${this.baseUrl}/predmeti/${username}`);
  }

  getProfessorsNotTeachingCourse(idPredmeta: number): Observable<Nastavnik[]> {
    return this.http.get<Nastavnik[]>(`${this.baseUrl}/nePredaju/${idPredmeta}`);
  }

  getProfessorsTeachingCourse(idPredmeta: number): Observable<Nastavnik[]> {
    return this.http.get<Nastavnik[]>(`${this.baseUrl}/Predaju/${idPredmeta}`);
  }

  addProfessorToCourse(korisnickoIme: string, idPredmeta: number): Observable<any> {
    const data = {
      korisnickoIme: korisnickoIme,
      idPredmeta: idPredmeta
    };
    return this.http.post(`${this.baseUrl}/dodajPracenje`, data);
  }
}
