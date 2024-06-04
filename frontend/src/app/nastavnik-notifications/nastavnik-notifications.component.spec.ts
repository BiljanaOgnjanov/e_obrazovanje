import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikNotificationsComponent } from './nastavnik-notifications.component';

describe('NastavnikNotificationsComponent', () => {
  let component: NastavnikNotificationsComponent;
  let fixture: ComponentFixture<NastavnikNotificationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NastavnikNotificationsComponent]
    });
    fixture = TestBed.createComponent(NastavnikNotificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
