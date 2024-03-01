import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { UserInfo } from 'src/app/model/UserInfo';
import { LoginService } from '../LoginService/login.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient:HttpClient,private route:Router) { }

  baseURL="http://localhost:8080/api/register/";

  response!:string;

  authObj:AuthInfo={
    username:"",
    password:""
  };

  loginService=inject(LoginService);

  async register(userInfo:UserInfo){
    const requestOptions: Object = {
      responseType: 'text'
    }
    if(userInfo.role === "SEEKER"){
      await this.httpClient.post<string>(this.baseURL+"user",userInfo,requestOptions).subscribe(res=>{
        this.response = res;
        console.log(this.response);
        this.authObj.username=userInfo.name.toString();
        this.authObj.password=userInfo.password.toString();
        this.loginService.loginUserAfterRegistration(this.authObj,"SEEKER");
        this.route.navigate(['/create-seeker-profile']);
      },
      (err)=>{
        if(err.status !== 200){
          alert(`Email id already registered`);
          this.route.navigate(['/login-seeker']);
        }else{
          alert(`Profile Created`);
        }
    });

    }
    else if(userInfo.role === "EMPLOYER"){
      await this.httpClient.post<string>(this.baseURL+"employer",userInfo,requestOptions).subscribe(res=>{
        this.response = res;
        console.log(this.response);
        this.authObj.username=userInfo.name.toString();
        this.authObj.password=userInfo.password.toString();
        this.loginService.loginUserAfterRegistration(this.authObj,"EMPLOYER");
        this.route.navigate(['/create-employer-profile']);
      },
      err=>{
        if(err.status !== 200){
          alert(`Email id already registered`);
          this.route.navigate(['/login-employer']);
        }else{
          alert(`Profile Created`);
        }
    });
    }
  }
}
