import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employer } from 'src/app/model/employer.model';

@Injectable({
  providedIn: 'root'
})
export class GetEmployerProfileService {

  employer!:Employer;

  constructor(private http:HttpClient) { }

  getEmployer():Observable<Employer>{

    let tokenString = "Bearer "+localStorage.getItem("token");


    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Employer>("http://localhost:8080/api/employer/v1/getprofile",{headers});
  }

  getProfilePic(){
    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get('http://localhost:8080/api/profilepic/getpic', { responseType: 'blob', headers});
  }
}
