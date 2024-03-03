import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';
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
  heroText = "Take the next step in your career journey with Career Crafter, the ultimate destination for job seekers like you. Explore endless opportunities, connect with top employers, and land your dream job today.";
  profile:string='';
  recommendedJobs:Listing[]=[]
  allJobs:Listing[]=[]
  currentRole:string|null = '';

  constructor(private searchJobsService:SearchJobsService,private getSeekerService:GetSeekerProfileService
    ,private route:Router,private store:Store<{currentUser:{currentUser: UserInfo}}>){}

  ngOnInit(){
    if(localStorage.getItem("currentRole")!=null){
      this.currentRole = localStorage.getItem('currentRole');
    }
    if(localStorage.getItem('loggedIn')!=null && localStorage.getItem('currentRole')==="SEEKER"){
      this.getSeekerService.getSeeker().subscribe(
        (res)=>{
          console.log(res.tagline);
          this.profile = res.tagline;
          this.store.select('currentUser').subscribe(
            (state)=>{
              console.log(`current user id ngrx store: ${state.currentUser.id}`);
              console.log(state.currentUser);
            }
          )
          this.searchJobsService.getAvailableJobs().subscribe(
            (data)=>{
              this.recommendedJobs = data;
              this.allJobs = data;
              console.log(this.recommendedJobs);
              this.recommendedJobs = this.recommendedJobs.filter(listing => listing.profile.includes(this.profile));
              console.log(this.recommendedJobs);
              if(this.recommendedJobs.length===0){
                this.recommendedJobs = this.allJobs.slice(0,3);
              }
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
      this.heroTitle = "Empower Your Business with Top Talent";
      this.heroText = "Welcome to Career Crafter, where finding the perfect candidates for your company has never been easier. Our platform connects you with a diverse pool of talented individuals ready to contribute to your organization's success.";
    }

  }



}
