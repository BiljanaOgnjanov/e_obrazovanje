import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikCourseAdministrationComponent } from './nastavnik-course-administration.component';

describe('NastavnikCourseAdministrationComponent', () => {
  let component: NastavnikCourseAdministrationComponent;
  let fixture: ComponentFixture<NastavnikCourseAdministrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikCourseAdministrationComponent]
    });
    fixture = TestBed.createComponent(NastavnikCourseAdministrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
