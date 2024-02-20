import { TestBed } from '@angular/core/testing';

import { CreateSeekerProfileService } from './create-seeker-profile.service';

describe('CreateSeekerProfileService', () => {
  let service: CreateSeekerProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateSeekerProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
