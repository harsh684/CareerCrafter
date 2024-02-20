import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { seekerRoleGuard } from './seeker-role.guard';

describe('seekerRoleGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => seekerRoleGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
