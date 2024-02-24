import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GetSeekerProfileService {

  constructor(private http:HttpClient) { }

  getCrafterResume(){}

  getResumeFile(){

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get("http://localhost:8080/api/resumedoc/download/ca753bb4-b5c6-46ca-8a5d-fdbfae8f1935",{headers});
  
  
  }
}
