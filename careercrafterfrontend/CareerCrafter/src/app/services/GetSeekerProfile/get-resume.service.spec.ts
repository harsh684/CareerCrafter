import { TestBed } from '@angular/core/testing';

import { GetResumeService } from './get-resume.service';

describe('GetResumeService', () => {
  let service: GetResumeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetResumeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
