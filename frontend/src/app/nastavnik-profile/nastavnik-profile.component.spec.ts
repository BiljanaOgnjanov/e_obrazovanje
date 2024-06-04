import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikProfileComponent } from './nastavnik-profile.component';

describe('NastavnikProfileComponent', () => {
  let component: NastavnikProfileComponent;
  let fixture: ComponentFixture<NastavnikProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikProfileComponent]
    });
    fixture = TestBed.createComponent(NastavnikProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
