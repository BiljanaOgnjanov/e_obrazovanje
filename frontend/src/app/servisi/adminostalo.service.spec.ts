import { TestBed } from '@angular/core/testing';

import { AdminostaloService } from './adminostalo.service';

describe('AdminostaloService', () => {
  let service: AdminostaloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminostaloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
