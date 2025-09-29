import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { ProfileComponent } from './components/profile/profile.component';
// import { ExamComponent } from './components/exam/exam.component';
import { UploadComponent } from './components/upload/upload.component';
import { GradeComponent } from './components/grade/grade.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { AdminComponent } from './components/admin/admin.component';
import { ExamBookingComponent } from './components/exam-booking/exam-booking.component';
import { FinancialCardComponent } from './components/financial-card/financial-card.component';
import { SubjectManagementComponent } from './components/subject-management/subject-management.component';
import { ExamComponent } from './components/exam/exam.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'admin',
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN'] },
    children: [
      { path: '', redirectTo: 'upravljanje-korisnicima', pathMatch: 'full' },
      { path: 'upravljanje-korisnicima', component: AdminComponent },
      { path: 'upravljanje-predmetima', component: SubjectManagementComponent },
    ],
  },
  {
    path: 'student',
    canActivate: [AuthGuard],
    data: { roles: ['STUDENT'] },
    children: [
      { path: '', redirectTo: 'pocetna', pathMatch: 'full' },
      { path: 'pocetna', component: ProfileComponent },
      { path: 'predmeti', component: SubjectsComponent },
      { path: 'ispiti', component: ExamComponent },
      { path: 'dokumenti', component: UploadComponent },
      { path: 'prijava-ispita', component: ExamBookingComponent },
      { path: 'finansijska-kartica', component: FinancialCardComponent },
    ],
  },
  {
    path: 'professor',
    canActivate: [AuthGuard],
    data: { roles: ['PROFESSOR'] },
    children: [
      { path: '', redirectTo: 'pocetna', pathMatch: 'full' },
      { path: 'pocetna', component: ProfileComponent },
      { path: 'predmeti', component: SubjectsComponent },
      { path: 'ocena', component: GradeComponent },
      { path: 'zakazivanje-ispita', component: ScheduleComponent },
    ],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
];
