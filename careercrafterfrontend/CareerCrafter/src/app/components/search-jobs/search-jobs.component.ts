import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  removeListing :number[]=[]

  jobsList:Listing[]=[];
  allList:Listing[]=[];
  filterRole:string = "All";
  filterDepartment:string = "All";
  filterLocation:string = "All";
  // locationDataField: Object = {text: 'location', value: 'location'};
  // departmentDataField: Object = {text: 'department', value: 'department'};
  // profileDataField: Object = {text: 'profile', value: 'profile'};

  constructor(private searchJobsService:SearchJobsService,private route:Router,private activatedRoute:ActivatedRoute){}

  ngOnInit(){

    this.activatedRoute.queryParams.subscribe(
      (params)=>{
        if(params['listingId']){
          this.removeListing.push(params['listingId'].value);
        }
      }
    )

    this.searchJobsService.getAvailableJobs()
    .subscribe((list)=>{
      this.jobsList=list;
      this.allList=list;
      this.jobsList = this.jobsList.filter(listing => !this.removeListing.includes(listing.listingId));
      console.log(this.jobsList);
    });
  }

  apply(listing:Listing){

    const encodeData = encodeURIComponent(JSON.stringify(listing));
  
    this.route.navigate(['/show-listing-details/'],{queryParams: {listing: encodeData}});

    // this.applyForJob.
    // this.getSeekerProfile.getResumeFile().subscribe(
    //   (res)=>{
    //     console.log(res);
    //     this.resumeFile=res;
    //   }
    // )
  }

  doFilter(){
    console.log(this.filterDepartment);
    console.log(this.filterRole);
    this.jobsList = this.allList.filter(job =>
      (this.filterRole === 'All' || job.profile === this.filterRole) &&
      (this.filterDepartment === 'All' || job.department === this.filterDepartment) &&
      (this.filterLocation === 'All' || job.location === this.filterLocation)
    );
    this.jobsList = this.jobsList.filter(listing => !this.removeListing.includes(listing.listingId));
  }

}
