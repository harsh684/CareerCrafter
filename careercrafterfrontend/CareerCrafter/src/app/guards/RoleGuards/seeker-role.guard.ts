import { CanActivateFn } from '@angular/router';

export const seekerRoleGuard: CanActivateFn = (route, state) => {
  return true;
};
