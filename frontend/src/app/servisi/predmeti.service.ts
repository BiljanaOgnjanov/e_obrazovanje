import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { mojiPredmetiDTO } from '../modeli/mojiPredmetiDTO';
import { ispitniRezultat } from '../modeli/ispitniRezultat';

@Injectable({
  providedIn: 'root'
})
export class PredmetiService {

  url = 'http://localhost:8080/student';

  constructor(private http: HttpClient) { }

  svaPracenjaPredmeta(username:string){
    return this.http.get<mojiPredmetiDTO[]>(`${this.url}/svaPracenjaPredmeta/${username}`);
  }

  detaljiPracenjaPredmeta(id_pracenja_predmeta:number){
    return this.http.get<ispitniRezultat[]>(`${this.url}/rezultati/${id_pracenja_predmeta}`);
  }

}
