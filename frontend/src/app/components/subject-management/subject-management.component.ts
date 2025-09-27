import { Component, OnInit, TemplateRef, ViewChild, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SubjectService, Subject } from '../../services/subject.service';
import { Student } from '../../services/student.service';
import { Professor } from '../../services/professor.service';
import { TableComponent, TableColumn } from '../table/table.component';

@Component({
  selector: 'app-subject-management',
  imports: [CommonModule, FormsModule, TableComponent],
  templateUrl: './subject-management.component.html',
})
export class SubjectManagementComponent implements OnInit {
  @ViewChild('customTemplate', { static: true }) customTemplate!: TemplateRef<any>;
  private subjectService = inject(SubjectService);

  courses: Subject[] = [];
  selectedCourse: Subject | null = null;
  students: Student[] = [];
  professors: Professor[] = [];

  activeTab: 'students' | 'professors' = 'students';
  selectedEntityId: string | null = null;

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses() {
    this.subjectService.getAll().subscribe((res) => {
      this.courses = res;
      if (this.courses.length) this.selectCourse(this.courses[0]);
    });
  }

  selectCourse(course: Subject) {
    this.selectedCourse = course;
    this.loadCourseData();
  }

  loadCourseData() {
    if (!this.selectedCourse) return;

    if (this.activeTab === 'students') {
      this.subjectService.getSubjectStudents(this.selectedCourse.id).subscribe((res) => {
        this.students = res;
      });
    } else {
      this.subjectService.getSubjectProfessors(this.selectedCourse.id).subscribe((res) => {
        this.professors = res;
      });
    }
  }

  removeEntity(entityId: string) {
    if (!this.selectedCourse) return;

    if (this.activeTab === 'students') {
      this.subjectService
        .deleteStudent({ courseId: this.selectedCourse.id, studentId: entityId })
        .subscribe(() => {
          alert('Uspesno Obrisano');
          this.loadCourseData();
        });
    } else {
      this.subjectService
        .deleteProfessor({ courseId: this.selectedCourse.id, professorId: entityId })
        .subscribe(() => {
          alert('Uspesno Obrisano');
          this.loadCourseData();
        });
    }
  }

  getColumns(): TableColumn[] {
    return [
      { value: 'firstName', label: 'Ime' },
      { value: 'lastName', label: 'Prezime' },
      { value: 'customTemplate', label: 'Obrisi', customTemplate: this.customTemplate },
    ];
  }

  getData(): any[] {
    if (this.activeTab === 'students') return this.students;
    return this.professors;
  }
}
