import { TestBed } from '@angular/core/testing';

import { ResumedbService } from './resumedb.service';

describe('ResumedbService', () => {
  let service: ResumedbService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResumedbService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
