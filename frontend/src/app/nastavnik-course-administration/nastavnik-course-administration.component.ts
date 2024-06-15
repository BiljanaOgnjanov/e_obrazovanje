import { Component, OnInit } from '@angular/core';
import { NastavniksaradniciService } from '../servisi/nastavniksaradnici.service';
import { Predmet } from '../modeli/predmet';
import { Nastavnik } from '../modeli/nastavnik';

@Component({
  selector: 'app-nastavnik-course-administration',
  templateUrl: './nastavnik-course-administration.component.html',
  styleUrls: ['./nastavnik-course-administration.component.css']
})
export class NastavnikCourseAdministrationComponent implements OnInit{
  ulogovan: Nastavnik = new Nastavnik();
  predmeti: Predmet[] = [];
  selectedPredmet: Predmet | null = null;
  isEditing = false;

  constructor(private nastavniksaradniciService: NastavniksaradniciService) {}

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);
      this.loadCourses();
    }
  }

  loadCourses(): void {
    this.nastavniksaradniciService.getCoursesByProfessor(this.ulogovan.username).subscribe(
      (data) => {
        this.predmeti = data;
      },
      (error) => {
        console.error('Error loading courses:', error);
      }
    );
  }

  onCourseSelect(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    const courseId = selectElement.value;

    if (courseId) {
      const id = parseInt(courseId, 10);
      this.nastavniksaradniciService.getCourseById(id).subscribe(
        (course) => {
          this.selectedPredmet = course;
        },
        (error) => {
          console.error('Error loading course details:', error);
        }
      );
    }
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  saveCourse(): void {
    if (this.selectedPredmet) {
      this.nastavniksaradniciService.updateCourse(this.selectedPredmet.idPredmeta, this.selectedPredmet).subscribe(
        (updatedCourse) => {
          this.selectedPredmet = updatedCourse;
          this.isEditing = false;
        },
        (error) => {
          console.error('Error updating course:', error);
        }
      );
    }
  }
}
