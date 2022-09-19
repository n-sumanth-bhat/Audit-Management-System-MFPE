import { TestBed } from '@angular/core/testing';

import { SeverityService } from './severity.service';

describe('SeverityService', () => {
  let service: SeverityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeverityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
