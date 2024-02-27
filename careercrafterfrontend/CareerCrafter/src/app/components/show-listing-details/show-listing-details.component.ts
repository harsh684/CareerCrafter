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

  constructor(private getSeekerProfile:GetSeekerProfileService, private activatedRoute: ActivatedRoute,private route:Router){}

  ngOnInit(){
    this.getSeekerProfile.getResumeFile().subscribe(
      (res)=>{
        this.resumeFile=res;
      });

      this.activatedRoute.queryParams.subscribe(
        (params)=>{
          if(params['listing']){
            const decodedData = decodeURIComponent(params['listing']);
            this.listing = JSON.parse(decodedData);
            console.log(this.listing);
            this.requiredSkills = this.listing.reqSkills;
          }
        }
      )
  }

  apply(listingId:number){
    this.route.navigate(['/apply-for-job/'+listingId]);
  }
}
