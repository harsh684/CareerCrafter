import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobSeeker } from 'src/app/model/jobseeker.model';

@Injectable({
  providedIn: 'root'
})
export class GetSeekerProfileService {

  constructor(private http:HttpClient) { }

  getCrafterResume(){}

  // getResumeFile(){

  //   let tokenString = "Bearer "+localStorage.getItem("token");

  //   const headers =  new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     'Access-Control-Allow-Origin': 'http://localhost:4200'
  //   }).set("Authorization",tokenString);

  //   return this.http.get("http://localhost:8080/api/resumedoc/download/ca753bb4-b5c6-46ca-8a5d-fdbfae8f1935",{headers});
  
  
  // }

  getSeeker():Observable<JobSeeker>{
    
    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<JobSeeker>("http://localhost:8080/api/seeker/getseeker",{headers});
 }

  getProfilePic(){
    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get('http://localhost:8080/api/profilepic/getpic', { responseType: 'blob', headers});
  }

  getAppliedListingIds(){
    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<number[]>("http://localhost:8080/api/seeker/getAppledListingList",{headers});
  }
}
