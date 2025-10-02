import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TableColumn } from '../table/table.component';
import { StudentService, Student } from '../../services/student.service';
import { ProfessorService, Professor } from '../../services/professor.service';
import { SubjectService, Subject } from '../../services/subject.service';
import { AuthService } from '../../services/auth.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.component.html',
})
export class AdminComponent implements OnInit {
  private studentService = inject(StudentService);
  private professorService = inject(ProfessorService);
  private subjectService = inject(SubjectService);
  private adminService = inject(AdminService);
  private auth = inject(AuthService);
  user = this.auth.currentUser();

  students: Student[] = [];
  professors: Professor[] = [];
  subjects: Subject[] = [];
  courses: Subject[] = [];

  role: any = '';
  activeTab: 'students' | 'professors' | 'subjects' = 'students';
  activeTitle: string = 'Studenti';
  showModal = false;
  showAssignCourseModal = false;
  editing: any = null;
  selectedEntity: any = null;
  selectedCourseId: string | null = null;
  searchText: string = '';

  ngOnInit(): void {
    this.adminService.getById(this.user?.id!).subscribe((res) => {
      this.role = res.role;
    });
    this.loadData();
    this.subjectService.getAll().subscribe((res) => (this.courses = res));
  }

  loadData() {
    if (this.activeTab === 'students') {
      this.activeTitle = 'Studenti';
      this.studentService.getAll().subscribe((res) => (this.students = res));
    } else if (this.activeTab === 'professors') {
      this.activeTitle = 'Profesori';
      this.professorService.getAll().subscribe((res) => (this.professors = res));
    } else {
      this.activeTitle = 'Predmeti';
      this.subjectService.getAll().subscribe((res) => (this.subjects = res));
    }
  }

  getColumns(): TableColumn[] {
    if (this.activeTab === 'students') {
      return [
        { value: 'firstName', label: 'Ime' },
        { value: 'lastName', label: 'Prezime' },
        { value: 'studentNumber', label: 'Broj indeksa' },
        { value: 'courseManagement', label: 'Dodeli Predmet' },
        { value: 'actions', label: 'Akcije' },
      ];
    } else if (this.activeTab === 'professors') {
      return [
        { value: 'firstName', label: 'Ime' },
        { value: 'lastName', label: 'Prezime' },
        { value: 'role', label: 'Pozicija' },
        { value: 'courseManagement', label: 'Dodeli Predmet' },
        ...(this.role === 'SUPER_ADMIN' ? [{ value: 'actions', label: 'Akcije' }] : []),
      ];
    } else {
      return [
        { value: 'name', label: 'Naziv' },
        { value: 'year', label: 'Godina' },
        { value: 'espb', label: 'Broj kredita (ESPB)' },
        ...(this.role === 'SUPER_ADMIN' ? [{ value: 'actions', label: 'Akcije' }] : []),
      ];
    }
  }

  roleLabels: Record<string, string> = {
    TEACHER: 'Profesor',
    ASSISTENT: 'Asistent',
    DEMONSTRATOR: 'Demonstrator',
  };

  getData(): any[] {
    if (this.activeTab === 'students') {
      return this.students.map((s) => ({
        ...s,
        coursesDisplay:
          s.courses?.map((c: Subject) => c.name).join(', ') || 'Nema dodeljenih predmeta',
        courseManagement: 'course-management',
        selectedCourseId: null,
        assignedCourses: s.courses || [],
      }));
    }
    if (this.activeTab === 'professors') {
      return this.professors.map((p) => ({
        ...p,
        coursesDisplay:
          p.courses?.map((c: Subject) => c.name).join(', ') || 'Nema dodeljenih predmeta',
        courseManagement: 'course-management',
        selectedCourseId: null,
        assignedCourses: p.courses || [],
      }));
    }
    return this.subjects;
  }

  openModal(item?: Student | Professor | Subject) {
    this.editing = item ? { ...item } : {};
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.editing = null;
  }

  save() {
    if (this.activeTab === 'students') {
      if (this.editing.id) {
        this.studentService.update(this.editing.id, this.editing).subscribe(() => this.loadData());
      } else {
        this.studentService.create(this.editing).subscribe(() => this.loadData());
      }
    } else if (this.activeTab === 'professors') {
      if (this.editing.id) {
        this.professorService
          .update(this.editing.id, this.editing)
          .subscribe(() => this.loadData());
      } else {
        this.professorService.create(this.editing).subscribe(() => this.loadData());
      }
    } else {
      if (this.editing.id) {
        this.subjectService.update(this.editing.id, this.editing).subscribe(() => this.loadData());
      } else {
        this.subjectService.create(this.editing).subscribe(() => this.loadData());
      }
    }
    this.closeModal();
  }

  delete(id: string) {
    if (this.activeTab === 'students') {
      this.studentService.delete(id).subscribe(() => this.loadData());
    } else if (this.activeTab === 'professors') {
      this.professorService.delete(id).subscribe(() => this.loadData());
    } else {
      this.subjectService.delete(id).subscribe(() => this.loadData());
    }
  }

  openAssignCourseModal(entity: Student | Professor) {
    this.selectedEntity = entity;
    this.selectedCourseId = null;
    this.showAssignCourseModal = true;
  }

  closeAssignCourseModal() {
    this.showAssignCourseModal = false;
    this.selectedEntity = null;
    this.selectedCourseId = null;
  }

  assignCourse() {
    if (!this.selectedEntity || !this.selectedCourseId) return;

    if (this.activeTab === 'students') {
      this.subjectService
        .assignStudent({ courseId: this.selectedCourseId, studentId: this.selectedEntity.id })
        .subscribe(() => this.loadData());
    } else if (this.activeTab === 'professors') {
      this.subjectService
        .assignProfessor({ courseId: this.selectedCourseId, professorId: this.selectedEntity.id })
        .subscribe(() => this.loadData());
    }

    this.closeAssignCourseModal();
  }

  assignCourseToEntity(entityId: string) {
    if (!this.selectedCourseId) {
      return;
    }

    if (this.activeTab === 'students') {
      this.subjectService
        .assignStudent({ courseId: this.selectedCourseId, studentId: entityId })
        .subscribe({
          next: () => {
            alert('Uspesno Dodat Predemt');
            this.loadData();
          },
          error: (error) => {
            console.error('Greska prilikom dodavanja studenta:', error);
          },
        });
    } else if (this.activeTab === 'professors') {
      this.subjectService
        .assignProfessor({ courseId: this.selectedCourseId, professorId: entityId })
        .subscribe({
          next: () => {
            alert('Uspesno Dodat Predmet');
            this.loadData();
          },
          error: (error) => {
            console.error('Greska prilikom dodavanja predmeta:', error);
          },
        });
    }
  }

  getAvailableCourses(entity: any): Subject[] {
    const assignedCourses = entity.assignedCourses || entity.courses || [];
    if (assignedCourses.length === 0) return this.courses;

    const assignedCourseIds = assignedCourses.map((c: Subject) => c.id);
    return this.courses.filter((course) => !assignedCourseIds.includes(course.id));
  }

  onDropdownFocus() {
    this.subjectService.getAll().subscribe((res) => {
      this.courses = res;
    });
  }

  onCourseSelectionChange(row: any) {
    this.selectedCourseId = row.selectedCourseId;
  }

  getFilteredData(): any[] {
    const data = this.getData();
    if (!this.searchText) return data;

    return data.filter((item) => {
      const searchLower = this.searchText.toLowerCase();
      return Object.values(item).some((value) => String(value).toLowerCase().includes(searchLower));
    });
  }
}
