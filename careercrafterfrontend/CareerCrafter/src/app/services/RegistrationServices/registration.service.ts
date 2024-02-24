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
        this.loginService.loginUser(this.authObj);
        return this.response;
      },
      err=>{
        alert(`Error in Service ${err}`);
        this.route.navigate(['/login-seeker']);
    });
    }
    else if(userInfo.role === "EMPLOYER"){
      await this.httpClient.post<string>(this.baseURL+"employer",userInfo,requestOptions).subscribe(res=>{
        this.response = res;
        console.log(this.response);
        this.authObj.username=userInfo.name.toString();
        this.authObj.password=userInfo.password.toString();
        this.loginService.loginUser(this.authObj);
        return this.response;
      },
      err=>{
        alert(`Error in Service ${err}`);
        this.route.navigate(['/login-employer']);
    });
    }
  }
}
