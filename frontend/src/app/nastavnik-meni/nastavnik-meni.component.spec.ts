import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikMeniComponent } from './nastavnik-meni.component';

describe('NastavnikMeniComponent', () => {
  let component: NastavnikMeniComponent;
  let fixture: ComponentFixture<NastavnikMeniComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikMeniComponent]
    });
    fixture = TestBed.createComponent(NastavnikMeniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
