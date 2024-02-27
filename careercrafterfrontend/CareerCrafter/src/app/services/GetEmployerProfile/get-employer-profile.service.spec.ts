import { TestBed } from '@angular/core/testing';

import { GetEmployerProfileService } from './get-employer-profile.service';

describe('GetEmployerProfileService', () => {
  let service: GetEmployerProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetEmployerProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
