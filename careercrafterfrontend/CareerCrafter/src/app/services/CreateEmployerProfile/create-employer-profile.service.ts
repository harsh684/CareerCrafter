import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Employer } from 'src/app/model/employer.model';

@Injectable({
  providedIn: 'root'
})
export class CreateEmployerProfileService {

  response:string = "";

  constructor(private http:HttpClient,private route:Router) { }

  async createEmployerProfile(employer: Employer) {

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    await this.http.post<string>("http://localhost:8080/api/employer/v1/createprofile",employer,{headers})
    .subscribe(
      (res)=>{
        this.response = res
      });

    return this.response;
  }
}
