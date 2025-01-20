import { Component, OnInit } from '@angular/core';
import { Student } from '../modeli/student';
import { Nastavnik } from '../modeli/nastavnik';
import { AdminostaloService } from '../servisi/adminostalo.service';

@Component({
  selector: 'app-admin-korisnici',
  templateUrl: './admin-korisnici.component.html',
  styleUrls: ['./admin-korisnici.component.css']
})
export class AdminKorisniciComponent implements OnInit{

  students: Student[] = [];
  professors: Nastavnik[] = [];
  showStudents: boolean = true; // Toggle state
  selectedUser: Student | Nastavnik | null = null;

  constructor(private adminService: AdminostaloService) {}

  ngOnInit(): void {
    this.fetchStudents();
    this.fetchProfessors();
  }

  // Fetch students
  fetchStudents(): void {
    this.adminService.getAllStudents().subscribe((data) => {
      this.students = data;
    });
  }

  // Fetch professors
  fetchProfessors(): void {
    this.adminService.getAllProfessors().subscribe((data) => {
      this.professors = data;
    });
  }

  // Toggle between students and professors
  toggleView(showStudents: boolean): void {
    this.showStudents = showStudents;
  }

  // Delete a user
  deleteUser(username: string): void {
    this.adminService.deleteUser(username).subscribe(() => {
      this.showStudents ? this.fetchStudents() : this.fetchProfessors();
    });
  }

  // Add or edit a user
  saveUser(user: Student | Nastavnik): void {
    if (this.showStudents) {
      this.adminService.addStudent(user as Student).subscribe(() => this.fetchStudents());
    } else {
      this.adminService.addProfessor(user as Nastavnik).subscribe(() => this.fetchProfessors());
    }
  }

  // Open user for editing
  editUser(user: Student | Nastavnik): void {
    this.selectedUser = { ...user }; // Clone the user to edit
  }

  // Reset selected user
  cancelEdit(): void {
    this.selectedUser = null;
  }
  // Sorting methods
  sortStudentsBy(field: keyof Student): void {
    this.students.sort((a, b) => {
      const valueA = a[field] ?? '';
      const valueB = b[field] ?? '';
      return String(valueA).localeCompare(String(valueB));
    });
  }

  sortProfessorsBy(field: keyof Nastavnik): void {
    this.professors.sort((a, b) => {
      const valueA = a[field] ?? '';
      const valueB = b[field] ?? '';
      return String(valueA).localeCompare(String(valueB));
    });
  }
  createNewUser(): void {
    if (this.showStudents) {
      this.editUser(new Student());
    } else {
      this.editUser(new Nastavnik());
    }
  }
}
