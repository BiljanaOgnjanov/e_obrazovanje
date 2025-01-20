import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {HttpClientModule} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { MeniComponent } from './meni/meni.component';
import { NastavnikComponent } from './nastavnik/nastavnik.component';
import { HeaderComponent } from './header/header.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { StudentLayoutComponent } from './student-layout/student-layout.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { StudentCourseSelectionComponent } from './student-course-selection/student-course-selection.component';
import { StudentCourseDashboardComponent } from './student-course-dashboard/student-course-dashboard.component';
import { StudentDocumentsComponent } from './student-documents/student-documents.component';
import { StudentPaymentsComponent } from './student-payments/student-payments.component';
import { StudentNotificationsComponent } from './student-notifications/student-notifications.component';
import { FormsModule } from '@angular/forms';
import { LogoutComponent } from './logout/logout.component';
import { NastavnikProfileComponent } from './nastavnik-profile/nastavnik-profile.component';
import { NastavnikMeniComponent } from './nastavnik-meni/nastavnik-meni.component';
import { NastavnikLayoutComponent } from './nastavnik-layout/nastavnik-layout.component';
import { NastavnikNotificationsComponent } from './nastavnik-notifications/nastavnik-notifications.component';
import { NastavnikCourseAdministrationComponent } from './nastavnik-course-administration/nastavnik-course-administration.component';
import { NastavnikAssociateAdministrationComponent } from './nastavnik-associate-administration/nastavnik-associate-administration.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { AdminMeniComponent } from './admin-meni/admin-meni.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { AdminKorisniciComponent } from './admin-korisnici/admin-korisnici.component';
import { AdminCoursesComponent } from './admin-courses/admin-courses.component';
import { AdminAkcijeComponent } from './admin-akcije/admin-akcije.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminLoginComponent,
    MeniComponent,
    NastavnikComponent,
    HeaderComponent,
    NotFoundComponent,
    StudentLayoutComponent,
    StudentProfileComponent,
    StudentCourseSelectionComponent,
    StudentCourseDashboardComponent,
    StudentDocumentsComponent,
    StudentPaymentsComponent,
    StudentNotificationsComponent,
    LogoutComponent,
    NastavnikProfileComponent,
    NastavnikMeniComponent,
    NastavnikLayoutComponent,
    NastavnikNotificationsComponent,
    NastavnikCourseAdministrationComponent,
    NastavnikAssociateAdministrationComponent,
    AdminLayoutComponent,
    AdminMeniComponent,
    AdminProfileComponent,
    AdminKorisniciComponent,
    AdminCoursesComponent,
    AdminAkcijeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
