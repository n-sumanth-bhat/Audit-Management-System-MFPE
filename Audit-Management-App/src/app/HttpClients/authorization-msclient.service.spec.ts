import { TestBed } from '@angular/core/testing';

import { AuthorizationMSClientService } from './authorization-msclient.service';

describe('AuthorizationMSClientService', () => {
  let service: AuthorizationMSClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorizationMSClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
