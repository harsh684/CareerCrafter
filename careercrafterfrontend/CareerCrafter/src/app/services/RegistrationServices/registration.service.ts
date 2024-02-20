import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserInfo } from 'src/app/model/UserInfo';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient:HttpClient) { }

  baseURL="http://localhost:8080/api/register/";

  response!:string;
  register(userInfo:UserInfo){
    const requestOptions: Object = {
      responseType: 'text'
    }
    if(userInfo.role === "SEEKER"){
      this.httpClient.post<string>(this.baseURL+"user",userInfo,requestOptions).subscribe(res=>{console.log(res)});
    }
    else if(userInfo.role === "EMPLOYER"){
      this.httpClient.post<string>(this.baseURL+"employer",userInfo,requestOptions).subscribe(res=>{console.log(res)});
    }
    return this.response;
  }
}
