import { TestBed } from '@angular/core/testing';

import { SeekerApplicaionService } from './seeker-applicaion.service';

describe('SeekerApplicaionService', () => {
  let service: SeekerApplicaionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeekerApplicaionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
