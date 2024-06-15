import { TestBed } from '@angular/core/testing';

import { NastavniksaradniciService } from './nastavniksaradnici.service';

describe('NastavniksaradniciService', () => {
  let service: NastavniksaradniciService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NastavniksaradniciService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
