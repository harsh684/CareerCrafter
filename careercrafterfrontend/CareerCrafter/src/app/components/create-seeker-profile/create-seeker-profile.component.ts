import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInfo } from 'src/app/model/UserInfo';
import { JobSeeker } from 'src/app/model/jobseeker.model';
import { CreateSeekerProfileService } from 'src/app/services/CreateSeekerProfile/create-seeker-profile.service';
import { GetCurrentUserService } from 'src/app/services/GetCurrentUser/get-current-user.service';

@Component({
  selector: 'app-create-seeker-profile',
  templateUrl: './create-seeker-profile.component.html',
  styleUrls: ['./create-seeker-profile.component.css']
})
export class CreateSeekerProfileComponent {

  createProfileForm!:FormGroup;

  submitted=false;

  isGenderSelected: boolean = false;
  isValidGender=false;
  pictureUrlLink="assets/default-user-profile-img.png";
  currentUser:UserInfo={
    id:0,
    name:"",
    email:"",
    password:"",
    role:"SEEKER",
    roleId:0
  };

  seeker:JobSeeker={
    seekerId: 0,
    seekerName: "",
    seekerGender: "",
    tagline: "",
    email: "",
    summary: "",
    dateOfBirth: new Date(),
    phoneNumber: "",
    address: "",
    country: "",
    currentSalary: 0,
    resume: {
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
    },
    applications: [],
    profilePic:{
      pictureId:"",
      name: "",
      type: "",
      data: null,
      role: ""
    },
  }

  constructor(private formBuilder:FormBuilder,private userInfo:GetCurrentUserService, private createSeekerProfileService:CreateSeekerProfileService,private route:Router){}

  ngOnInit():void{

    this.userInfo.getCurrentUser().subscribe(user=>{
      this.currentUser=user;
    },
    err=>{
      console.log(err);
      window.location.reload();
    });

    this.createProfileForm = this.formBuilder.group({
      tagline: ['',Validators.required], //, Validators.pattern('^[^\d\W]*$')
      seekerGender: ['',Validators.required],
      summary: ['',Validators.required],
      dateOfBirth: ['',Validators.required],
      phoneNumber: ['',Validators.required], //,Validators.minLength(10),Validators.maxLength(10),Validators.pattern('\\d{10}')
      address: ['',Validators.required],
      country: ['',Validators.required],
      currentSalary: ['',Validators.required] //,Validators.pattern('^\d+(\.\d+)?$')
    })
  }

  get f(){
    return this.createProfileForm.controls;
  }

  checkSelectedValue():boolean {
    if(this.isGenderSelected = this.f['seekerGender'].value !== ''){
      if(this.f['seekerGender'].value === 'Male' || this.f['seekerGender'].value === 'Female' || this.f['seekerGender'].value === 'Prefer Not To Say'){
        this.isValidGender=true;
        return true;
      }else{
        this.isValidGender=false;
        return false;
      }
    }
    return false;
  }

  createProfile(){
    this.submitted=true;

    if(!this.createProfileForm.invalid){
      this.seeker.seekerName=this.currentUser.name;
      this.seeker.email=this.currentUser.email;
      this.seeker.tagline=this.f['tagline'].value;
      this.seeker.address=this.f['address'].value;
      this.seeker.seekerGender=this.f['seekerGender'].value;
      this.seeker.country=this.f['country'].value;
      this.seeker.currentSalary=this.f['currentSalary'].value;
      this.seeker.phoneNumber=this.f['phoneNumber'].value;
      this.seeker.summary=this.f['summary'].value;
      this.seeker.dateOfBirth=this.f['dateOfBirth'].value;

      console.log(this.seeker);

      let response;

      this.createSeekerProfileService.createSeekerProfile(this.seeker).then((res)=>{
        response=res;
      });

      if(response!=""){
        alert( `Profile created`);
        this.route.navigate(['/edit-resume']);
      }else{
        alert('Some Error occured');
        this.route.navigate(['/create-employer-profile']);
      };

    }else{
      this.submitted=false;
      return ;
    }

  }

  selectFile(data:any){

  }

}
