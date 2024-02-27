import { TestBed } from '@angular/core/testing';

import { EmployerRoleGuardGuard } from './employer-role-guard.guard';

describe('EmployerRoleGuardGuard', () => {
  let guard: EmployerRoleGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(EmployerRoleGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
