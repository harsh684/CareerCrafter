import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Resume } from 'src/app/model/resume.model';

@Component({
  selector: 'app-display-resume',
  templateUrl: './display-resume.component.html',
  styleUrls: ['./display-resume.component.css']
})
export class DisplayResumeComponent {

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

  constructor(private activatedRoute:ActivatedRoute){
    this.activatedRoute.queryParams.subscribe(params => {
      if(params['resume']){
        this.resume = JSON.parse(params['resume']);
        console.log(this.resume);
      }
      else{
        console.log("error")
      }
    });
  }



}
