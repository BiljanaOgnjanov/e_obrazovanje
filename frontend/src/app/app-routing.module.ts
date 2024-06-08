import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { StudentLayoutComponent } from './student-layout/student-layout.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { StudentCourseDashboardComponent } from './student-course-dashboard/student-course-dashboard.component';
import { StudentCourseSelectionComponent } from './student-course-selection/student-course-selection.component';
import { StudentDocumentsComponent } from './student-documents/student-documents.component';
import { StudentPaymentsComponent } from './student-payments/student-payments.component';
import { StudentNotificationsComponent } from './student-notifications/student-notifications.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { NastavnikLayoutComponent } from './nastavnik-layout/nastavnik-layout.component';
import { NastavnikProfileComponent } from './nastavnik-profile/nastavnik-profile.component';
import { NastavnikCourseAdministrationComponent } from './nastavnik-course-administration/nastavnik-course-administration.component';
import { NastavnikAssociateAdministrationComponent } from './nastavnik-associate-administration/nastavnik-associate-administration.component';
import { NastavnikNotificationsComponent } from './nastavnik-notifications/nastavnik-notifications.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { AdminCoursesComponent } from './admin-courses/admin-courses.component';
import { AdminKorisniciComponent } from './admin-korisnici/admin-korisnici.component';

const routes: Routes = [
  {path:'login', component:LoginComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: "student",
    component: StudentLayoutComponent,
    children: [
      { path: '', component: StudentProfileComponent },
      { path: 'predmeti-dashboard', component: StudentCourseDashboardComponent },
      { path: 'prijave-predmeta', component: StudentCourseSelectionComponent },
      { path: 'dokumenti', component: StudentDocumentsComponent },
      { path: 'placanja', component: StudentPaymentsComponent },
      { path: 'obavestenja', component: StudentNotificationsComponent },
    ],
  },
  {
    path:"nastavnik",
    component:NastavnikLayoutComponent,
    children:[
      {path: '', component: NastavnikProfileComponent},
      {path:'administracija-predmeta', component:NastavnikCourseAdministrationComponent},
      {path:'administracija-saradnika', component:NastavnikAssociateAdministrationComponent},
      {path:'obavestenja', component:NastavnikNotificationsComponent}
    ],
  },
  {path:'adminlogin', component:AdminLoginComponent},
  {
    path:"admin",
    component:AdminLayoutComponent,
    children:[
      {path: '', component: AdminProfileComponent},
      {path:'administracija-predmeta', component:AdminCoursesComponent},
      {path:'administracija-studenta', component:AdminKorisniciComponent}
    ],
  },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
