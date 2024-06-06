import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { mojiPredmetiDTO } from '../modeli/mojiPredmetiDTO';

@Injectable({
  providedIn: 'root'
})
export class PredmetiService {

  url = 'http://localhost:8080/student';

  constructor(private http: HttpClient) { }

  svaPracenjaPredmeta(username:string){
    return this.http.get<mojiPredmetiDTO[]>(`${this.url}/svaPracenjaPredmeta/${username}`);
  }

}
