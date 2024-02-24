import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from '../../model/UserInfo';
import { HttpClient } from '@angular/common/http';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { Store } from '@ngrx/store';
import { GetCurrentUserService } from '../GetCurrentUser/get-current-user.service';
import { updateCurrentUserState } from 'src/app/store/actions/current-user.action';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  res!: string;

  cu!:UserInfo[];

  constructor(private httpClient:HttpClient,private store:Store<{currentUsers:{currentUsers:UserInfo[]}}>,private getCurrentUserService:GetCurrentUserService) {
    store.select('currentUsers').subscribe( (currentUsersState:{currentUsers:UserInfo[]})=>{

      this.cu = currentUsersState.currentUsers;

    })
   }

  baseURL="http://localhost:8080/api/register/";

  loginUser(authInfo: AuthInfo):Promise<string>{
    console.log(authInfo);
    localStorage.setItem("token","");
    const requestOptions: Object = {
      responseType: 'text'
    }
    return new Promise<string>((resolve,reject)=>{
      this.httpClient.post<string>(this.baseURL + "authenticate", authInfo, requestOptions)
      .subscribe((res)=>{
        localStorage.setItem("token",res);
        resolve(res);
        this.getCurrentUserService.getCurrentUser().subscribe((res)=>{
          const currentUser:UserInfo={
            id:res.id,
            email:res.email,
            name:res.name,
            password:res.password,
            role:res.role,
            roleId:res.roleId
        }
            localStorage.setItem("currentRole",currentUser.role);
            this.store.dispatch(updateCurrentUserState({currentUser}));
        });
      },
      (err)=>{
        reject(err);
      });
    })

    // obj.subscribe(
    //   (res)=>{
    //     console.log(res);
    //     if(res!== null || res!== '')
    //       localStorage.setItem("token",res);
        
    //       else
    //         localStorage.removeItem("token");
    //     }),
    // (error:any)=>{
    //   console.log(error);
    // };
   
  }

}
