import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Listing } from 'src/app/model/listing.model';

@Injectable({
  providedIn: 'root'
})
export class SearchJobsService {

  constructor(private http:HttpClient) { }

  getAvailableJobs():Observable<Listing[]>{

    let list:Listing[]=[];

    let tokenString = "Bearer "+localStorage.getItem("token");

    const headers =  new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
    }).set("Authorization",tokenString);

    return this.http.get<Listing[]>("http://localhost:8080/api/seeker/searchjobs",{headers});

  }

}
