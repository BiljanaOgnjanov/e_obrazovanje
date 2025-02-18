import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentDocumentsComponent } from './student-documents.component';

describe('StudentDocumentsComponent', () => {
  let component: StudentDocumentsComponent;
  let fixture: ComponentFixture<StudentDocumentsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentDocumentsComponent]
    });
    fixture = TestBed.createComponent(StudentDocumentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
