import { Component, OnInit } from '@angular/core';
import { AdminpredmetiService } from '../servisi/adminpredmeti.service';
import { Predmet } from '../modeli/predmet';
import { Administrator } from '../modeli/administrator';

@Component({
  selector: 'app-admin-courses',
  templateUrl: './admin-courses.component.html',
  styleUrls: ['./admin-courses.component.css']
})
export class AdminCoursesComponent implements OnInit{

  ulogovan: Administrator =new Administrator();
  predmeti: Predmet[] = [];
  newPredmet: Predmet = new Predmet();
  addingCourse: boolean = false;

  constructor(private adminPredmetiService: AdminpredmetiService) { }

  ngOnInit(): void {
    let x = localStorage.getItem("ulogovan");
    if (x != null) {
      this.ulogovan = JSON.parse(x);

    this.loadCourses();
    }
  }

  loadCourses(): void {
    this.adminPredmetiService.getAllCourses().subscribe(data => {
      this.predmeti = data;
    });
  }

  addCourse(): void {
    this.adminPredmetiService.createCourse(this.newPredmet).subscribe(() => {
      this.loadCourses();
      this.newPredmet = new Predmet();
      this.addingCourse = false;
    });
  }

  deleteCourse(id: number): void {
    console.log(`Deleting course with ID: ${id}`);

    this.adminPredmetiService.deleteCourse(id).subscribe(() => {
      console.log('Course deleted successfully.');
      this.loadCourses();
    });
  }

  toggleAddCourseForm(): void {
    this.addingCourse = !this.addingCourse;
  }
}
