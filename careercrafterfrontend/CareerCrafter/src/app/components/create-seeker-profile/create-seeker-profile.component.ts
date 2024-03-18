import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInfo } from 'src/app/model/UserInfo';
import { JobSeeker } from 'src/app/model/jobseeker.model';
import { CreateSeekerProfileService } from 'src/app/services/CreateSeekerProfile/create-seeker-profile.service';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';

@Component({
  selector: 'app-create-seeker-profile',
  templateUrl: './create-seeker-profile.component.html',
  styleUrls: ['./create-seeker-profile.component.css']
})
export class CreateSeekerProfileComponent {

  createProfileForm!:FormGroup;

  submitted=false;

  changedPassword:string = '';

  isGenderSelected: boolean = false;
  isValidGender=false;
  pictureUrlLink="assets/default-user-profile-img.png";
  pictureFile:any;

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

  userInfo!:UserInfo;
  // private userInfo:GetCurrentUserService
  constructor(private formBuilder:FormBuilder,
     private createSeekerProfileService:CreateSeekerProfileService,private route:Router,
     private getSeekerProfile:GetSeekerProfileService){
      const temp = localStorage.getItem('currentUser');
      if(temp!=null){
        this.userInfo = JSON.parse(temp);
        this.currentUser = this.userInfo;
      }
     }

  ngOnInit():void{

    // this.userInfo.getCurrentUser().subscribe(user=>{
    //   this.currentUser=user;
    // },
    // err=>{
    //   console.log(err);
    //   window.location.reload();
    // });
    // console.log(this.currentUser);

    console.log(`Inside Create Seeker Profile Component`);

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

    this.getSeekerProfile.getSeeker().subscribe(
      (seeker)=>{
        this.createProfileForm.patchValue({
          tagline: seeker.tagline, 
          seekerGender: seeker.seekerGender!=null?seeker.seekerGender:'Select a Gender',
          summary: seeker.summary,
          dateOfBirth: seeker.dateOfBirth,
          phoneNumber: seeker.phoneNumber,
          address: seeker.address,
          country: seeker.country,
          currentSalary: seeker.currentSalary
        })

        this.getSeekerProfile.getProfilePic().subscribe((response)=>{
          const reader = new FileReader();
          reader.onload = () => {
            this.pictureUrlLink = reader.result as string;
          };
          reader.readAsDataURL(response);   
        },
        (err)=>{
          console.log(err);
        })
        console.log(this.pictureUrlLink);
      },
      (err)=>{
        console.log(err);
      }
    );
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

      this.createSeekerProfileService.createSeekerProfile(this.seeker).subscribe(
        (res)=>{
          alert( `Profile created`);
          this.route.navigate(['/edit-resume']);
          response=res;
        },
        (err)=>{
          if(err.status === 200){
            alert( `Profile created`);
            this.route.navigate(['/edit-resume']);
          }
          else{
            alert("Some error occurred");
            this.route.navigate(['/create-employer-profile']);
          }
        }
      )
    }else{
      this.submitted=false;
      return ;
    }

  }

  updateProfile(){
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

      this.createSeekerProfileService.updateSeekerProfile(this.seeker).subscribe(
        (res)=>{
          response=res;
        },
        (err)=>{
          if(err.status === 200){
            alert( `Profile updated`);
            this.route.navigate(['/']);
          }
          else{
            alert("Some error occurred");
            this.route.navigate(['/create-employer-profile']);
          }
        });

      if(this.pictureFile!=null){
        this.createSeekerProfileService.uploadProfilePicture(this.pictureFile).then((res)=>{
          response=res;
          alert( `Profile updated`);
        // window.location.reload();
        this.route.navigate(['/']);
        },
        (err)=>{
          alert(`Error occured`);
          console.log(err);
        })
      }

      if(response!=""){
        alert( `Profile updated`);
        // window.location.reload();
        this.route.navigate(['/']);
      }else{
        alert('Some Error occured');
        this.route.navigate(['/create-employer-profile']);
      };

    }else{
      this.submitted=false;
      return ;
    }

  }

  changePassword(){
    this.createSeekerProfileService.changePassword(this.changedPassword).subscribe(
      (res)=>{
        alert( `Password changed`);
        this.changedPassword = '';
      },
      (err)=>{
        if(err.status === 200){
          alert( `Password changed`);
          this.changedPassword = '';
        }else if(err.status === 403){
          alert( `Session expired`);
          this.route.navigate(['/']);
        }else{
          alert( 'Something went wrong');
          this.changedPassword = '';
        }
      }
    )
  }

  selectFile(event: any){
    if(event.target.files){
      var reader = new FileReader();
      this.pictureFile=event.target.files[0];
      console.log(this.pictureFile)
      reader.readAsDataURL(event.target.files[0]);
      reader.onload=(event: any)=>{
        this.pictureUrlLink=event.target.result;
      }

      if(this.pictureFile!=null){
        this.createSeekerProfileService.uploadProfilePicture(this.pictureFile).then((res)=>{
          if(res !== ''){
            alert(`Successfully uploaded`);
            this.route.navigate(['/edit-resume']);
          }
        },
        (err)=>{
          if(err.status === 200){
            alert(`Successfully uploaded`);
            this.route.navigate(['/edit-resume'])
          }else{
            alert(`Error in uploading file`)
          }
        })
      }
    }
  }

}
