import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Applications } from 'src/app/model/applications.model';
import { Listing } from 'src/app/model/listing.model';
import { Resume } from 'src/app/model/resume.model';
import { ResumeDoc } from 'src/app/model/resumedoc.model';

@Injectable({
  providedIn: 'root'
})
export class ListingService {

  requestURL = "http://localhost:8080/api/employer/v1/"

  constructor(private http:HttpClient) { }

  viewApplicationsForListing(listingId:number){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    
    return this.http.get<Applications[]>(this.requestURL+"viewforlisting/"+listingId,{headers});
  }

  getMYListings():Observable<Listing[]>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    
    return this.http.get<Listing[]>(this.requestURL+"getemployerListings",{headers});
  }

  changeListingStatus(listingID:number,status:string){

    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);
    console.log(listingID+ ' '+ status);
    return this.http.put<any>(this.requestURL+'changelistingstatus/'+listingID,status,{headers});
  }

  getCrafterResume(resumeId:number){

    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Resume>(this.requestURL+'getresumebyid/'+resumeId,{headers});
  }

  getResumeDoc(resumeId:number){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/pdf',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    let pdfUrlLink='';

    this.http.get('http://localhost:8080/api/resumedoc/downloadbyresumeid/'+8,{ responseType: 'blob' , headers})
    .subscribe((response)=>{
        
        var fileURL = URL.createObjectURL(response);
        window.open(fileURL); 
    })

    
  }
  
  changeApplicationStatus(applicationId:number,status:string){
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    console.log(applicationId+ ' '+ status);
    
    return this.http.put<any>(this.requestURL+'changeapplicationstatus/'+applicationId,status,{headers});
  }
  
}


