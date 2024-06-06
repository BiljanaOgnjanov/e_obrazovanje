import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Uplata } from '../modeli/uplata';
import { Observable } from 'rxjs';
import { Dkmnt } from '../modeli/dkmnt';

@Injectable({
  providedIn: 'root'
})
export class DokumentaplacanjaService {

  url = 'http://localhost:8080/student';

  constructor(private http: HttpClient) { }

  dokumentiKorisnika(username:string){
    return this.http.get<Dkmnt[]>(`${this.url}/dokumenti/${username}`);
  }
  uplateKorisnika(username:string){
    return this.http.get<Uplata[]>(`${this.url}/uplate/${username}`);
  }

  postaviDokument(uploadData: FormData): Observable<any> {
    return this.http.post<any>(`${this.url}/dokumenti/upload`, uploadData);
  }

  obrisiDokument(idDokumenta: number): Observable<any> {
    return this.http.delete(`${this.url}/dokumenti/obrisi/${idDokumenta}`, { responseType: 'text' });
  }
}
