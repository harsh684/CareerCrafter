import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resume } from 'src/app/model/resume.model';

@Injectable({
  providedIn: 'root'
})
export class ResumedbService {

  requestURL = 'http://localhost:8080/api/employer/v1'

  constructor(private http:HttpClient) { }

  getResumeDb():Observable<Resume[]>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/pdf',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Resume[]>(this.requestURL+"/managecrafterresume",{headers});
  }

  getResumeById(resumeId:string):Observable<Resume>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/pdf',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Resume>(this.requestURL+"/getresumebyid/"+resumeId,{headers});
  }

  getSeekerNameByResumeId(resumeId:number):Observable<string>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/pdf',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    
    return this.http.get<string>(this.requestURL+"/getSeekerNameByResumeId/"+resumeId,{headers});
  }

}
