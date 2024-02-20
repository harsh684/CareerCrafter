import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from '../../model/UserInfo';
import { HttpClient } from '@angular/common/http';
import { AuthInfo } from 'src/app/model/AuthRequest';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  res!: string;

  constructor(private httpClient:HttpClient) { }

  baseURL="http://localhost:8080/api/register/";

  loginUser(authInfo: AuthInfo){
    console.log(authInfo);
    const requestOptions: Object = {
      responseType: 'text'
    }
    this.httpClient.post<string>(this.baseURL + "authenticate", authInfo, requestOptions)
    .subscribe(
      (res)=>{
        //console.log(res);
        if(res!== null || res!== '')
          localStorage.setItem("token",res);
        
          else
            localStorage.removeItem("token");
        }),
    (error:any)=>{
      console.log(error);
    };
   
  }

}
