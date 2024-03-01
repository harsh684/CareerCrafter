import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Applications } from 'src/app/model/applications.model';
import { ApplyForJobService } from 'src/app/services/ApplyForJob/apply-for-job.service';

import { GetResumeService } from 'src/app/services/GetSeekerProfile/get-resume.service';

@Component({
  providers: [DatePipe],
  selector: 'app-applicationy-for-job',
  templateUrl: './apply-for-job.component.html',
  styleUrls: ['./apply-for-job.component.css']
})
export class ApplyForJobComponent {

  currentDate =new Date();

  resumeFile:any;
  listingId:number = 0 ;
  summary:string = "";

  seekerApplication:Applications={
    applicationId: 0,
    companyName: '',
    profile: '',
    appliedDate: new Date,
    status: '',
    coverLetter: '',
    resume: {
      resumeId: 0,
      address: "",
      languages: [],
      skills: [],
      referenceLinks: [],
      accomplishments: [],
      experiences: [],
      education: [],
      projects: [],
      certifications: [],
      resumeFile: {
        docId: "",
        name: "",
        type: "",
        data: null
      }
    }
  } 

  constructor(private activatedRoute: ActivatedRoute,private resumeService:GetResumeService,
    private applicationyService:ApplyForJobService,private route:Router){}
  ngOnInit(){
    this.activatedRoute.params.subscribe(params=>{
      this.listingId=params['listingId'];
      console.log(this.listingId)
    })
  }

  uploadResume(event:any){
    if(event.target.files){
      this.resumeFile =event.target.files[0];
      console.log(this.resumeFile);
      this.resumeService.uploadResumeDoc(this.resumeFile);
    }
  }
  apply(application:Applications){
    this.seekerApplication.companyName = application.companyName;
    this.seekerApplication.profile = application.profile;
    this.seekerApplication.status = 'Pending';
    this.seekerApplication.coverLetter = this.summary;
    this.seekerApplication.appliedDate = new Date();
    console.log(this.seekerApplication);
    this.applicationyService.apply(this.listingId,this.seekerApplication).subscribe(
      (res)=>{
        alert("Applied");
        this.route.navigate(['/search-jobs'],{queryParams: {
          listingId: this.listingId
        }});
      },
      (err)=>{
        if(err.status === 200){
          alert("Applied");
          this.route.navigate(['/search-jobs'],{queryParams: {
            listingId: this.listingId
          }});
        }else{
          alert("Failed to apply");
        }
      }
    );
  }
}
