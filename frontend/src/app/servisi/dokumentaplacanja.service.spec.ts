import { TestBed } from '@angular/core/testing';

import { DokumentaplacanjaService } from './dokumentaplacanja.service';

describe('DokumentaplacanjaService', () => {
  let service: DokumentaplacanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DokumentaplacanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
