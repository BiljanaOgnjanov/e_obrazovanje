import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    component: LoginComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin'] },
  },
  {
    path: 'student',
    component: LoginComponent,
    canActivate: [AuthGuard],
    data: { roles: ['student'] },
  },
  {
    path: 'profesor',
    component: LoginComponent,
    canActivate: [AuthGuard],
    data: { roles: ['profesor'] },
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' },
];
