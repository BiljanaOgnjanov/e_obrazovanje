import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { environment } from '../environments/environment';

import { firstValueFrom } from 'rxjs';

export type UserType = 'ADMIN' | 'STUDENT' | 'PROFESSOR';

export interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  userType: UserType;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _currentUser = signal<User | null>(null);
  currentUser = this._currentUser.asReadonly();

  constructor(private http: HttpClient) {
    this.loadUserFromStorage();
  }

  updateUser(user: User) {
    this._currentUser.set(user);
    localStorage.setItem('user', JSON.stringify(user));
  }

  async login(email: string, password: string): Promise<User | null> {
    const record = this.http.post(environment.apiUrl + '/auth/login', { email, password });

    try {
      const response = await firstValueFrom(record);
      this._currentUser.set(response as User);
      localStorage.setItem('user', JSON.stringify(response));
      return response as User;
    } catch (error) {
      console.error('Login failed!', error);
    }

    return null;
  }

  logout() {
    this._currentUser.set(null);
    localStorage.removeItem('user');
  }

  private loadUserFromStorage() {
    const data = localStorage.getItem('user');
    if (data) {
      this._currentUser.set(JSON.parse(data));
    }
  }
}
