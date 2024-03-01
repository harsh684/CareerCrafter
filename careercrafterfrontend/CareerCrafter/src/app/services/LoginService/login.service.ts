import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from '../../model/UserInfo';
import { HttpClient } from '@angular/common/http';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { Store } from '@ngrx/store';
import { GetCurrentUserService } from '../GetCurrentUser/get-current-user.service';
import { updateCurrentUserState } from 'src/app/store/actions/current-user.action';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  session:string|null = null;

  res!: string;

  cu!:UserInfo[];

  currentUser$:UserInfo={
    id: 0,
    email:'',
    name:'',
    password:'',
    role:'',
    roleId:0
  }

  constructor(private httpClient:HttpClient,private route:Router,private getCurrentUserService:GetCurrentUserService) {
    
   }

  baseURL="http://localhost:8080/api/register/";

  loginUser(authInfo: AuthInfo):Promise<string>{
    console.log(authInfo);
    localStorage.removeItem("token");
    localStorage.removeItem('loggedIn');
    // localStorage.removeItem('currentRole');
    const requestOptions: Object = {
      responseType: 'text'
    }
    return new Promise<string>((resolve,reject)=>{
      this.httpClient.post<string>(this.baseURL + "authenticate", authInfo, requestOptions)
      .subscribe((res)=>{
        localStorage.setItem("token",res);
        localStorage.setItem('loggedIn','true');
        resolve(res);
        this.getCurrentUserService.getCurrentUser().subscribe((res)=>{
          this.currentUser$.id = res.id;
          this.currentUser$.email = res.email;
          this.currentUser$.name= res.name;
          this.currentUser$.password = res.password;
          this.currentUser$.role = res.role;
          this.currentUser$.roleId = res.roleId;
          localStorage.setItem("currentRole",this.currentUser$.role);
          this.session = "logged in";
          this.route.navigate(['/']);
            // this.store.dispatch(updateCurrentUserState({currentUser: currentUser$}));
        });
      },
      (err)=>{
        if(err.status === 200){
          this.session = "logged in";
          localStorage.setItem('loggedIn','true');
          alert('Logged in')
          this.route.navigate(['/home']);
        }else{
          if(err.status === 200){
            localStorage.setItem('loggedIn','true');
            alert('Logged in');
          this.route.navigate(['/home']);
          }else if(err.status === 403){
            alert('Wrong Credentials');
          }else{
            alert('Something went wrong');
          }
        }
      });
    })
  }

  loginUserAfterRegistration(authInfo: AuthInfo,role:string):Promise<string>{
    console.log(authInfo);
    localStorage.removeItem("token");
    localStorage.removeItem('loggedIn');
    // localStorage.removeItem('currentRole');
    const requestOptions: Object = {
      responseType: 'text'
    }
    return new Promise<string>((resolve,reject)=>{
      this.httpClient.post<string>(this.baseURL + "authenticate", authInfo, requestOptions)
      .subscribe((res)=>{
        localStorage.setItem("token",res);
        localStorage.setItem('loggedIn','true');
        localStorage.setItem('currentRole',role);
        resolve(res);
        this.getCurrentUserService.getCurrentUser().subscribe((res)=>{
          
          this.currentUser$.id = res.id;
          this.currentUser$.email = res.email;
          this.currentUser$.name= res.name;
          this.currentUser$.password = res.password;
          this.currentUser$.role = res.role;
          this.currentUser$.roleId = res.roleId;

          this.session = "logged in";
          if(role === "SEEKER"){
            this.route.navigate(['/create-seeker-profile']);
          }else if(role === "EMPLOYER"){
            this.route.navigate(['/create-employer-profile']);
          }
            // this.store.dispatch(updateCurrentUserState({currentUser: currentUser$}));
        });
      },
      (err)=>{
        if(err.status === 200){
          this.session = "logged in";
          localStorage.setItem('loggedIn','true');
          alert('Logged in')
          this.route.navigate(['/home']);
        }else{
          if(err.status === 200){
            localStorage.setItem('loggedIn','true');
            alert('Logged in');
          this.route.navigate(['/home']);
          }else if(err.status === 403){
            alert('Wrong Credentials');
          }else{
            alert('Something went wrong');
          }
        }
      });
    })
  }

  logout(){
    if(confirm(`Are you sure to Log out?`)){
      this.session=null;
      localStorage.clear();
      this.route.navigate(['/homepage']);
    }else{
      window.location.reload();
    }
}
}
