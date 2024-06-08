import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMeniComponent } from './admin-meni.component';

describe('AdminMeniComponent', () => {
  let component: AdminMeniComponent;
  let fixture: ComponentFixture<AdminMeniComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminMeniComponent]
    });
    fixture = TestBed.createComponent(AdminMeniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
