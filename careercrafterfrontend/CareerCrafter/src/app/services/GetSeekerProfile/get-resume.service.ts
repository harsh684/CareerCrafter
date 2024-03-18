import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Resume } from 'src/app/model/resume.model';
import { ResumeDoc } from 'src/app/model/resumedoc.model';

@Injectable({
  providedIn: 'root'
})
export class GetResumeService {

  response:string="";

  constructor(private http:HttpClient){}

  uploadResumeDoc(resumeFile:any){
    let tokenString = "Bearer "+localStorage.getItem("token");

    const formData = new FormData();
    formData.append('resume',resumeFile);


    const headers =  new HttpHeaders({
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    this.http.post<any>("http://localhost:8080/api/resumedoc/seeker/uploadresumedoc",formData,{headers})
    .subscribe(
      (res)=>{
        this.response = res;
        return this.response;
      },
      (err)=>{
        if(err === 200){
          alert(`Resume Uploaded`)
        }
        console.log(err);
      });
  }



  editresume(resume:Resume){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.put('http://localhost:8080/api/seeker/createprofile/resume',resume,{headers});
   }

   getCrafterResume(){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Resume>('http://localhost:8080/api/seeker/getCrafterResume',{headers});
   }

   getSeekerNameByResumeId(resumeId:number):Observable<string>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    
    return this.http.get<string>("http://localhost:8080/api/seeker/v1/getSeekerNameByResumeId/"+resumeId,{headers});
  }
}
