import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Listing } from 'src/app/model/listing.model';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';
import { SearchJobsService } from 'src/app/services/SearchJobs/search-jobs.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {

  heroTitle = "Get hired by your dream company";
  heroText = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt ut inventore facilis dignissimos harum aspernatur, culpa sapiente atque magnam, iure quibusdam vitae blanditiis facere dolore fugit! Possimus labore temporibus harum.";
  profile:string='';
  recommendedJobs:Listing[]=[]
  currentRole:string|null = '';

  constructor(private searchJobsService:SearchJobsService,private getSeekerService:GetSeekerProfileService
    ,private route:Router){}

  ngOnInit(){
    this.currentRole = localStorage.getItem('currentRole');
    if(localStorage.getItem('loggedIn')!=null && localStorage.getItem('currentRole')==="SEEKER"){
      this.getSeekerService.getSeeker().subscribe(
        (res)=>{
          console.log(res.tagline);
          this.profile = res.tagline;
          this.searchJobsService.getAvailableJobs().subscribe(
            (data)=>{
              this.recommendedJobs = data;
              console.log(this.recommendedJobs);
              this.recommendedJobs = this.recommendedJobs.filter(listing => listing.profile.includes(this.profile));
              console.log(this.recommendedJobs);
            },
            (err)=>{
              if(err.status === 403){
                localStorage.clear();
                this.route.navigate(['/login-seeker']);
              }
            }
          )
        }
        ,
        (err)=>{
          if(err.status === 403){
            this.currentRole = null;
          }
        }
      )
    }
    else if(localStorage.getItem('loggedIn')!=null && localStorage.getItem('currentRole')==="EMPLOYER"){
      this.heroTitle = "Hire the Ideal Candidate for your company";
      this.heroText = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt ut inventore facilis dignissimos harum aspernatur, culpa sapiente atque magnam, iure quibusdam vitae blanditiis facere dolore fugit! Possimus labore temporibus harum.";
    }

  }



}
