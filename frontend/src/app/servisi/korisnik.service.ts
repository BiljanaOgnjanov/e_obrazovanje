import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KorisnikResponse } from '../modeli/korisnikResponse';
import { Korisnik } from '../modeli/korisnik';


@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  url = 'http://localhost:8080/korisnici';

  constructor(private http: HttpClient) { }

  prijava(username: string, password: string) {
    const data = {
      username: username,
      password: password
    };
    return this.http.post<KorisnikResponse>(`${this.url}/prijava`, data);
  }

}
