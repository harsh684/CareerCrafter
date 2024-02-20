import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { employerRoleGuard } from './employer-role.guard';

describe('employerRoleGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => employerRoleGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
