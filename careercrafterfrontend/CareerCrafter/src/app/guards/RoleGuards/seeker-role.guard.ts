import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { map, take } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo';

export const seekerRoleGuard: CanActivateFn = (route, state) => {

  var isAuthorized = false;

  //const store = inject(Store);

  return true;

  // return store.pipe(
  //   select('currentUsers'),
  //   map(currentUsersState => currentUsersState.currentUsers[0].role === 'SEEKER'),
  //   take(1), 
  //   map(isSeeker => {
  //     if (!isSeeker) {
  //       console.log('role not authorized');
  //     }
  //     return isSeeker;
  //   })
  // )

  // store.select('currentUsers').subscribe((currentUsersState)=>{
  //   currentUsersState.currentUsers.pipe(map(user=>{
  //     const role = user.role;
  //     if (role === 'SEEKER') {
  //             console.log('authorized');
  //             isAuthorized = true;
  //           } else {
  //             console.log('role not authorized:', role);
  //             isAuthorized = false;
  //           }
  //   }))
  // })

  // store.select('currentUsers').subscribe((currentUsersState)=>{
  //   console.log(currentUsersState.currentUsers[0]);
  //   const role = currentUsersState.currentUsers[0].role;
  //   if(role == 'SEEKER'){
  //     console.log(`authorized ${role} `);
  //     return true;
  //   }else{
  //     console.log("role not authorized "+role)
  //     return false;
  //   }
  // });

  // return store.pipe(
  //   select('currentUsers'),
  //   map(currentUsersState => {
  //     const role =  currentUsersState.currentUsers[0].role;
  //     if (role == 'SEEKER') {
  //       console.log(`Authorized: ${role}`);
  //       return true;
  //     } else {
  //       console.log(`Role not authorized: ${role}`);
  //       return false;
  //     }
  //   })
  // );
};
