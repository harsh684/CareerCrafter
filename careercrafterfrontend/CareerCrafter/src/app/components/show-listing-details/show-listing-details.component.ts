import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Listing } from 'src/app/model/listing.model';
import { Skills } from 'src/app/model/skills.model';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';

@Component({
  selector: 'app-apply-for-job',
  templateUrl: './show-listing-details.component.html',
  styleUrls: ['./show-listing-details.component.css']
})
export class ShowListingDetails {

  resumeFile!:any;

  listing!:Listing;

  requiredSkills:Skills[]= [];

  appliedList:number[]=[]

  constructor(private getSeekerProfile:GetSeekerProfileService, private activatedRoute: ActivatedRoute,private route:Router){}

  ngOnInit(){
    // this.getSeekerProfile.getResumeFile().subscribe(
    //   (res)=>{
    //     this.resumeFile=res;
    //   });
      console.log(`Inside show listing details component`);
      
      this.activatedRoute.queryParams.subscribe(
        (params)=>{
          if(params['listing']){
            const decodedData = decodeURIComponent(params['listing']);
            this.listing = JSON.parse(decodedData);
            // console.log(this.listing);
            this.requiredSkills = this.listing.reqSkills;
          }
        }
      )

      this.getSeekerProfile.getAppliedListingIds().subscribe(
        (res)=>{
          this.appliedList = res;
          console.log(res);
        },
        (err)=>{
          if(err.status === 200){
            console.log(err.error.text);
          }else if(err.status === 403){
            localStorage.clear();
            this.route.navigate(['/login-employer']);
          }else{
            console.log(err);
          }
        })
  }

  apply(listingId:number){
    this.route.navigate(['/apply-for-job/'+listingId]);
  }
}
