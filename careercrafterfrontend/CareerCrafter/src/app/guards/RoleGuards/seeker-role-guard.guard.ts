import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo';

@Injectable({
  providedIn: 'root'
})
export class seekerRoleGuardGuard implements CanActivate {
  
  currentUser$!:Observable<UserInfo>;

  constructor(private route:Router){
  
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
      return new Observable<boolean | UrlTree>((observer) => {
        const role = localStorage.getItem('currentRole');
  
        console.log(`Inside Seeker Role guard. Role is: ${role}`);
  
        if (role === 'SEEKER') {
          observer.next(true);
          observer.complete();
        } else { 
          this.route.navigate(['/login-seeker']);          
          observer.next(false);
          observer.complete();
        }
      });
  }
  
}
