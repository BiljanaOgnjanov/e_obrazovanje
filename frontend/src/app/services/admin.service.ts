import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

export interface AdminRole {
  role: 'SUPER_ADMIN' | 'SUPPORT_ADMIN';
}

export interface Admin {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  role: AdminRole;
}

@Injectable({ providedIn: 'root' })
export class AdminService {
  private apiUrl = `${environment.apiUrl}/admins`;

  constructor(private http: HttpClient) {}

  getById(id: string): Observable<Admin> {
    return this.http.get<Admin>(`${this.apiUrl}/${id}`);
  }
}
