import { Injectable, signal } from '@angular/core';

export type Role = 'admin' | 'student' | 'profesor';

export interface User {
  username: string;
  role: Role;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _currentUser = signal<User | null>(null);
  currentUser = this._currentUser.asReadonly();

  constructor() {
    this.loadUserFromStorage();
  }

  login(username: string, password: string): boolean {
    const users: Record<string, { password: string; role: Role }> = {
      s: { password: 's', role: 'student' },
    };

    const record = users[username];

    if (record && record.password === password) {
      const user: User = { username, role: record.role };
      this._currentUser.set(user);
      // localStorage.setItem('user', JSON.stringify(user));
      return true;
    }

    return false;
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
