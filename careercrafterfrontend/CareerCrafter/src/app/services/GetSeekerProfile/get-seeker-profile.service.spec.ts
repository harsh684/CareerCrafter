import { TestBed } from '@angular/core/testing';

import { GetSeekerProfileService } from './get-seeker-profile.service';

describe('GetSeekerProfileService', () => {
  let service: GetSeekerProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetSeekerProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
