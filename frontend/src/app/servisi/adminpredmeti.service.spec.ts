import { TestBed } from '@angular/core/testing';

import { AdminpredmetiService } from './adminpredmeti.service';

describe('AdminpredmetiService', () => {
  let service: AdminpredmetiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminpredmetiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
