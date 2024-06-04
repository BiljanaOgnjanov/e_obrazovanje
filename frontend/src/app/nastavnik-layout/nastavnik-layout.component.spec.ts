import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikLayoutComponent } from './nastavnik-layout.component';

describe('NastavnikLayoutComponent', () => {
  let component: NastavnikLayoutComponent;
  let fixture: ComponentFixture<NastavnikLayoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikLayoutComponent]
    });
    fixture = TestBed.createComponent(NastavnikLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
