import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Applications } from 'src/app/model/applications.model';

@Injectable({
  providedIn: 'root'
})
export class ApplyForJobService {

  response:string = '';

  constructor(private http:HttpClient) { }

  apply(listingId:number,application:Applications){
    
    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.post<any>("http://localhost:8080/api/seeker/apply/"+listingId,application,{headers});
    
  }
}
