import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Applications } from 'src/app/model/applications.model';
import { SeekerApplicaionService } from 'src/app/services/SeekerApplicationService/seeker-applicaion.service';

@Component({
  selector: 'app-seeker-applications',
  templateUrl: './seeker-applications.component.html',
  styleUrls: ['./seeker-applications.component.css']
})
export class SeekerApplicationsComponent {

  applications:Applications[]=[];

  constructor(private route:Router,private applicationService:SeekerApplicaionService){
    this.applicationService.getMyApplication().subscribe(
      (res)=>{
        this.applications = res;
        console.log(`Inside Seeker Applications Component`);
      }
    )
  }

  getListing(applicationId:number){
    this.applicationService.getListing(applicationId).subscribe(
      (res)=>{
        const encodeData = encodeURIComponent(JSON.stringify(res));
  
        this.route.navigate(['/show-listing-details/'],{queryParams: {listing: encodeData}});

      }
    );
  }


}
