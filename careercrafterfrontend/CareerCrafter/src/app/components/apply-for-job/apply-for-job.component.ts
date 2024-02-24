import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Listing } from 'src/app/model/listing.model';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';

@Component({
  selector: 'app-apply-for-job',
  templateUrl: './apply-for-job.component.html',
  styleUrls: ['./apply-for-job.component.css']
})
export class ApplyForJobComponent {

  resumeFile!:any;

  listing!:Listing;

  constructor(private getSeekerProfile:GetSeekerProfileService, private route: ActivatedRoute){}

  ngOnInit(){
    this.getSeekerProfile.getResumeFile().subscribe(
      (res)=>{
        this.resumeFile=res;
      });

      this.route.queryParams.subscribe(
        (params)=>{
          if(params['listing']){
            const decodedData = decodeURIComponent(params['listing']);
            this.listing = JSON.parse(decodedData);
          }
        }
      )
  }

}
