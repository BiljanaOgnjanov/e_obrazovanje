import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentCourseDashboardComponent } from './student-course-dashboard.component';

describe('StudentCourseDashboardComponent', () => {
  let component: StudentCourseDashboardComponent;
  let fixture: ComponentFixture<StudentCourseDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentCourseDashboardComponent]
    });
    fixture = TestBed.createComponent(StudentCourseDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
