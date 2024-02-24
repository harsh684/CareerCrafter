import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Listing } from 'src/app/model/listing.model';
import { ResumeDoc } from 'src/app/model/resumedoc.model';
import { ApplyForJobService } from 'src/app/services/ApplyForJob/apply-for-job.service';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';
import { SearchJobsService } from 'src/app/services/SearchJobs/search-jobs.service';

@Component({
  selector: 'app-search-jobs',
  templateUrl: './search-jobs.component.html',
  styleUrls: ['./search-jobs.component.css']
})
export class SearchJobsComponent {

  jobsList:Listing[]=[];

  constructor(private searchJobsService:SearchJobsService,private route:Router){}

  ngOnInit(){
    this.searchJobsService.getAvailableJobs()
    .subscribe((list)=>{
      this.jobsList=list;
      console.log(this.jobsList);
    });
  }

  apply(listing:Listing){

    const encodeData = encodeURIComponent(JSON.stringify(listing));

    this.route.navigate(['/apply-for-job/'],{queryParams: {listing: encodeData}});

    // this.applyForJob.
    // this.getSeekerProfile.getResumeFile().subscribe(
    //   (res)=>{
    //     console.log(res);
    //     this.resumeFile=res;
    //   }
    // )
  }

}
