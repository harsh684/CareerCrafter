import { TestBed } from '@angular/core/testing';

import { CreateEmployerProfileService } from './create-employer-profile.service';

describe('CreateEmployerProfileService', () => {
  let service: CreateEmployerProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateEmployerProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
