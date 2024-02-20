import { CanActivateFn } from '@angular/router';

export const employerRoleGuard: CanActivateFn = (route, state) => {
  return true;
};
