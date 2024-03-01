import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Resume } from 'src/app/model/resume.model';
import { ResumedbService } from 'src/app/services/GetResumeDb/resumedb.service';

@Component({
  selector: 'app-display-resume',
  templateUrl: './display-resume.component.html',
  styleUrls: ['./display-resume.component.css']
})
export class DisplayResumeComponent {

  seekerName:string='';

  seekerMail:string='';

  resume:Resume={
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
    },
  };

  constructor(private activatedRoute:ActivatedRoute,private resumeService:ResumedbService,private route:Router){
    this.activatedRoute.queryParams.subscribe(params => {
      if(params['resume']){
        this.resume = JSON.parse(params['resume']);
        console.log(this.resume.skills);
      }
      else{
        console.log("error")
      }
    });
  }

  ngOnInit(){
    this.resumeService.getSeekerNameByResumeId(this.resume.resumeId).subscribe(
      (res)=>{
        console.log(res);
        const all:string[]=res.split(',');
        this.seekerName=all[0];
        this.seekerMail=all[1];
        console.log(this.seekerName);
      },
      (err)=>{
        if(err.status === 200){
          const all:string[]=err.error.text.split(',');
          this.seekerName=all[0];
          this.seekerMail=all[1];
        }else if(err.status === 403){
          localStorage.clear();
          this.route
        }
        else{
          alert('Could not get seeker name');
        }
      }
    )
  }



}
