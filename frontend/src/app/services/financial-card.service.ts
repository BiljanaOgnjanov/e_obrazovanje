import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface Transactions {
  id: string;
  amount: number;
  type: 'DEPOSIT' | 'EXAM_FEE' | 'REFUND';
  createdAt: string;
}

@Injectable({ providedIn: 'root' })
export class FinancialCardService {
  private baseUrl = environment.apiUrl + '/students';

  constructor(private http: HttpClient) {}

  getTransactions(studentId: string): Observable<Transactions[]> {
    return this.http.get<Transactions[]>(`${this.baseUrl}/${studentId}/card/transactions`);
  }
}
