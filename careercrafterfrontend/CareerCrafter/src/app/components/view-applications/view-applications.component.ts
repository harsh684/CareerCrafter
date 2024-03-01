import { Component } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
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

  fromDate:Date= new Date(2022, 0, 1);
  toDate:Date= new Date();

  applications:Applications[]=[];
  allApplications:Applications[]=[];
  filteredApplication:Applications[]=[];
  filterProfile='';

  crafterResume!:Resume;

  constructor(private activatedRoute:ActivatedRoute,private listingService:ListingService,
    private route:Router){
    // this.activatedRoute.queryParams.subscribe(params => {
    //   this.applications = JSON.parse(decodeURI(params['applications']));
    //   console.log(this.applications);
    // });
    this.activatedRoute.queryParams.subscribe(params => {
      this.applications = JSON.parse(params['applications']);
      this.allApplications= JSON.parse(params['applications']);
      console.log(this.applications);
    });
  }

  filter(){
    this.applications=this.allApplications;
      if (this.filterProfile === '' || this.filterProfile.toLowerCase() === 'all') {
        // If no profile is selected, assign all applications to filteredApplications
        this.applications = this.allApplications;
      } else {
        // If a profile is selected, filter applications by profile and assign the filtered list
        this.applications = this.allApplications.filter(app => app.profile.toLowerCase().includes(this.filterProfile.toLowerCase()));
      }
    
  }

  filterApplicationsByDateRange(){
    this.applications=this.allApplications;
    this.applications = this.applications.filter(application => {
        return application.appliedDate >= this.fromDate && application.appliedDate <= this.toDate;
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
