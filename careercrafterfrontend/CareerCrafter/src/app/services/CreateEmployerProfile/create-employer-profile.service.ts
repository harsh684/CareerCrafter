import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employer } from 'src/app/model/employer.model';

@Injectable({
  providedIn: 'root'
})
export class CreateEmployerProfileService {

  response:string = "";

  constructor(private http:HttpClient,private route:Router) { }

createEmployerProfile(employer: Employer):Observable<any> {

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.post<any>("http://localhost:8080/api/employer/v1/createprofile",employer,{observe: 'response',headers});
  }

  changePassword(password:string){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.put<string>("http://localhost:8080/api/register/changePassword",password,{headers});
  }
  
  updateEmployerProfile(employer: Employer){

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.put<string>("http://localhost:8080/api/employer/v1/updateprofile",employer,{observe: 'response',headers});

  }

  async uploadProfilePicture(profilepic:any){
    let tokenString = "Bearer "+localStorage.getItem("token");

    const formData = new FormData();
    formData.append('profilepic',profilepic);


    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    await this.http.post<string>("http://localhost:8080/api/profilepic/upload",formData,{headers})
    .subscribe(
      (res)=>{
        this.response = res
      });

    return this.response;
  }
}
