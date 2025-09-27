import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TableColumn, TableComponent } from '../table/table.component';
import { AuthService } from '../../services/auth.service';
import { Subject, SubjectService } from '../../services/subject.service';
import { ProfessorService } from '../../services/professor.service';
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-subjects',
  imports: [RouterModule, CommonModule, FormsModule, TableComponent],
  styleUrls: ['./subjects.component.css'],
  templateUrl: './subjects.component.html',
})
export class SubjectsComponent implements OnInit {
  private auth = inject(AuthService);
  private subjectService = inject(SubjectService);
  private professorService = inject(ProfessorService);
  private studentService = inject(StudentService);

  user = this.auth.currentUser();

  subjects: Subject[] = [];
  professorSubjects: any[] = [];
  currentStudents: any[] = [];

  selectedSubjectId: string | null = null;

  showRoleModal = false;
  currentRoleType: 'ASSISTENT' | 'DEMONSTRATOR' | null = null;
  currentSubjectForRole: string | null = null;
  availableRoles: any[] = [];

  showEditModal = false;
  editedSubject: any = null;

  ngOnInit(): void {
    if (this.user?.userType === 'STUDENT') {
      this.loadStudentSubjects(this.user.id);
    } else if (this.user?.userType === 'PROFESSOR') {
      this.loadProfessorSubjects(this.user.id);
    }
  }

  loadStudentSubjects(studentId: string) {
    this.studentService.getStudentSubjects(studentId).subscribe({
      next: (res) => (this.subjects = res),
      error: (err) => console.error('Error loading student subjects:', err),
    });
  }

  loadProfessorSubjects(professorId: string) {
    this.professorService.getProfessorSubjects(professorId).subscribe({
      next: (res) => {
        this.professorSubjects = res;
        this.professorSubjects.forEach((subject) => {
          this.loadSubjectProfessors(subject);
        });
      },
      error: (err) => console.error('Error loading professor subjects:', err),
    });
  }

  loadSubjectProfessors(subject: any) {
    this.subjectService.getSubjectProfessors(subject.id).subscribe({
      next: (res) => {
        subject.assistants = res.filter((p: any) => p.role === 'ASSISTENT');
        subject.demonstrators = res.filter((p: any) => p.role === 'DEMONSTRATOR');
      },
      error: (err) => console.error(`Error loading professors for subject ${subject.id}:`, err),
    });
  }

  viewStudents(subjectId: string) {
    this.selectedSubjectId = subjectId;
    this.subjectService.getSubjectStudents(subjectId).subscribe({
      next: (res) => (this.currentStudents = res),
      error: (err) => console.error('Error fetching students:', err),
    });
  }

  backToSubjects() {
    this.selectedSubjectId = null;
    this.currentStudents = [];
  }

  openEditModal(subject: any) {
    this.editedSubject = { ...subject };
    this.showEditModal = true;
  }

  closeEditModal() {
    this.showEditModal = false;
    this.editedSubject = null;
  }

  saveEdit() {
    if (!this.editedSubject) return;

    this.subjectService.update(this.editedSubject.id, this.editedSubject).subscribe({
      next: (updated: any) => {
        const index = this.professorSubjects.findIndex((s) => s.id === updated.id);
        if (index > -1) this.professorSubjects[index] = updated;
        this.closeEditModal();
      },
      error: () => {
        alert('Greška pri čuvanju izmene');
      },
    });
  }

  openRoleModal(subjectId: string, type: 'ASSISTENT' | 'DEMONSTRATOR') {
    this.currentSubjectForRole = subjectId;
    this.currentRoleType = type;
    this.showRoleModal = true;

    this.professorService.getAll().subscribe({
      next: (res) => {
        this.availableRoles = res.filter((p: any) => p.role === type);
      },
      error: (err) => console.error('Greska pri ucitavanju ruta:', err),
    });
  }

  closeRoleModal() {
    this.showRoleModal = false;
    this.currentRoleType = null;
    this.currentSubjectForRole = null;
    this.availableRoles = [];
  }

  assignRole(subjectId: string, professorId: string) {
    this.subjectService.assignProfessor({ courseId: subjectId, professorId }).subscribe({
      next: () => {
        const subject = this.professorSubjects.find((s) => s.id === subjectId);
        if (subject) this.loadSubjectProfessors(subject);
        this.closeRoleModal();
      },
      error: (err) => console.error('Greska pri dodeljivanju role:', err),
    });
  }

  removeRole(subjectId: string, professorId: string) {
    this.subjectService.deleteProfessor({ courseId: subjectId, professorId }).subscribe({
      next: () => {
        const subject = this.professorSubjects.find((s) => s.id === subjectId);
        if (subject) this.loadSubjectProfessors(subject);
      },
      error: (err) => console.error('Greska pri uklanjanju role:', err),
    });
  }

  getColumns(): TableColumn[] {
    if (this.user?.userType === 'STUDENT') {
      return [
        { value: 'name', label: 'Naziv' },
        { value: 'year', label: 'Godina' },
        { value: 'espb', label: 'Broj kredita (ESPB)' },
      ];
    } else {
      return this.selectedSubjectId
        ? [
            { value: 'firstName', label: 'Ime' },
            { value: 'lastName', label: 'Prezime' },
            { value: 'studentNumber', label: 'Broj indeksa' },
          ]
        : [
            { value: 'name', label: 'Naziv' },
            { value: 'year', label: 'Godina' },
            { value: 'espb', label: 'Broj kredita (ESPB)' },
            { value: 'Akcije', label: 'Akcije' },
          ];
    }
  }

  getData(): any[] {
    if (this.user?.userType === 'STUDENT') {
      return this.subjects;
    } else {
      return this.selectedSubjectId
        ? this.currentStudents
        : this.professorSubjects.map((subject) => ({
            ...subject,
            Akcije: `subject-${subject.id}`,
          }));
    }
  }

  getCurrentSubjectName(): string {
    if (!this.selectedSubjectId) return '';
    const subject = this.professorSubjects.find((s) => s.id === this.selectedSubjectId);
    return subject ? subject.name : '';
  }
}
