import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Resume } from 'src/app/model/resume.model';
import { ResumedbService } from 'src/app/services/GetResumeDb/resumedb.service';
import { ListingService } from 'src/app/services/Listings/listing.service';

@Component({
  selector: 'app-manage-resume-db',
  templateUrl: './manage-resume-db.component.html',
  styleUrls: ['./manage-resume-db.component.css']
})
export class ManageResumeDbComponent {

  allResume:Resume[]=[];
  seekerNames:string[]=[];
  constructor(private resumeService:ResumedbService,private listingService:ListingService,private route:Router){}

 ngOnInit(){
  this.resumeService.getResumeDb()
  .subscribe(
    (res)=>{
      this.allResume=res;
      console.log(res);
    }
  );
  
 }
 getResumeById(resumeId:number){
  this.listingService.getResumeDoc(resumeId);
}

getSeekerName(resumeId:number){
  this.resumeService.getSeekerNameByResumeId(resumeId).subscribe(
    (res)=>{
      console.log(res);
      return res;
    },
    (err)=>{
      if(err.status === 200){
        return err.error.text;
      }else if(err.status === 403){
        localStorage.clear();
        this.route.navigate(['/login-employer']);
      }
      else{
        alert('Could not get seeker name');
      }
    }
  )
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

}
