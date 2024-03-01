import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Applications } from 'src/app/model/applications.model';

@Injectable({
  providedIn: 'root'
})
export class SeekerApplicaionService {

  requestURL = 'http://localhost:8080/api/seeker/';

  constructor(private http:HttpClient) { }

  getMyApplication():Observable<Applications[]>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Applications[]>(this.requestURL+"getyourapplications",{headers});
  
  }

  getListing(applicaionId:number):Observable<Applications>{
    let tokenString = "Bearer " +localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    // /getListingByApplicationId/{applicationId}
    return this.http.get<Applications>(this.requestURL+"getListingByApplicationId/"+applicaionId,{headers});
  
  }
}
