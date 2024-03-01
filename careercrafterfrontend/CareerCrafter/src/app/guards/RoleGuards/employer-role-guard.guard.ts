import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployerRoleGuardGuard implements CanActivate {
  
  constructor(private route:Router){
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      return new Observable<boolean | UrlTree>((observer) => {
  
        console.log(`Inside Seeker Role guard. Role is: ${localStorage.getItem('currentRole')}`);
  
        if (localStorage.getItem('currentRole') === 'EMPLOYER') {
          observer.next(true);
          observer.complete();
        } else { 
          this.route.navigate(['/login-employer']);          
          observer.next(false);
          observer.complete();
        }
      });
  }
  
}
