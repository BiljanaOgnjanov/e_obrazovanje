import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikAssociateAdministrationComponent } from './nastavnik-associate-administration.component';

describe('NastavnikAssociateAdministrationComponent', () => {
  let component: NastavnikAssociateAdministrationComponent;
  let fixture: ComponentFixture<NastavnikAssociateAdministrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikAssociateAdministrationComponent]
    });
    fixture = TestBed.createComponent(NastavnikAssociateAdministrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
