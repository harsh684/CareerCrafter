import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo';
import { Applications } from 'src/app/model/applications.model';
import { Resume } from 'src/app/model/resume.model';
import { ListingService } from 'src/app/services/Listings/listing.service';
import { UserState } from 'src/app/store/reducers/current-user.reducer';

@Component({
  selector: 'app-view-applications',
  templateUrl: './view-applications.component.html',
  styleUrls: ['./view-applications.component.css']
})
export class ViewApplicationsComponent {

  applications:Applications[]=[];

  crafterResume!:Resume;

  constructor(private activatedRoute:ActivatedRoute,private listingService:ListingService,
    private route:Router){
    // this.activatedRoute.queryParams.subscribe(params => {
    //   this.applications = JSON.parse(decodeURI(params['applications']));
    //   console.log(this.applications);
    // });
    this.activatedRoute.queryParams.subscribe(params => {
      this.applications = JSON.parse(params['applications']);
      console.log(this.applications);
    });
  }

  getResumeById(resumeId:number){
    this.listingService.getResumeDoc(resumeId);
  }

  getCrafterResumeById(resumeId:number){
    this.listingService.getCrafterResume(resumeId).subscribe(
      res=>{
        console.log(res);
        this.route.navigate(['/display-resume'],{
          queryParams:{
            resume: JSON.stringify(res)
          }
        })
      }
    )
  }

  changeApplicationStatus(applicationId:number,status:string){
    this.listingService.changeApplicationStatus(applicationId,status)
    .subscribe(
      (res)=>{
        this.applications = this.applications.map(
          application=>{
            if (application.applicationId === applicationId) {
              application.status = status;
            }
            return application;
          }
        )
        console.log(res.value);
      },
      err=>{
        if(err.status === 200){
          this.applications = this.applications.map(
            application=>{
              if (application.applicationId === applicationId) {
                application.status = status;
              }
              return application;
            }
          )
          console.log(err.status);
        }else{
          alert('Could not update status');
        }
      }
      
    )
  }

}
