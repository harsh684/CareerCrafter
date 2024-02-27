import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
        alert(err);
        console.log(err);
      });
  }

}
