import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';
import { Employer } from 'src/app/model/employer.model';
import { CreateEmployerProfileService } from 'src/app/services/CreateEmployerProfile/create-employer-profile.service';
import { GetCurrentUserService } from 'src/app/services/GetCurrentUser/get-current-user.service';
import { GetEmployerProfileService } from 'src/app/services/GetEmployerProfile/get-employer-profile.service';



@Component({
  selector: 'app-create-employer-profile',
  templateUrl: './create-employer-profile.component.html',
  styleUrls: ['./create-employer-profile.component.css']
})
export class CreateEmployerProfileComponent {

  employer:Employer={
    employerId:0,
    name:"",
    employerGender:"",
    email:"",
    phno:"",
    address:"",
    companyName:"",
    listings:[],
    profilePic:{
      pictureId:"",
      name: "",
      type: "",
      data: null,
      role: ""
    }
  };

  currentUser:UserInfo={
    id:0,
    name:"",
    email:"",
    password:"",
    role:"Employer",
    roleId:0
  };

  userInfo=inject(GetCurrentUserService);
  createProfileService=inject(CreateEmployerProfileService);

  createProfileForm!:FormGroup;

  submitted=false;

  response!:string;

  pictureUrlLink:string|null="assets/default-user-profile-img.png";
  pictureFile:any;

  constructor(private formBuilder:FormBuilder,private route:Router,private getProfileService:GetEmployerProfileService){
   }

  

  ngOnInit():void{

    this.userInfo.getCurrentUser().subscribe(user=>{
      this.currentUser=user;
    },
    err=>{
      console.log(err);
      window.location.reload();
    });

    this.createProfileForm=this.formBuilder.group({
      //profilePicture:['',[Validators.required]],
      gender:['',[Validators.required]],
      phoneNumber:['',[Validators.required,Validators.minLength(10),Validators.maxLength(10),Validators.pattern('\\d{10}')]],
      address:['',[Validators.required]],
      companyName:['',[Validators.required]]

    });

    this.getProfileService.getEmployer().subscribe(
      (emp)=>{
        this.createProfileForm.patchValue({
          gender: emp.employerGender,
          phoneNumber: emp.phno,
          address: emp.address,
          companyName: emp.companyName
        })

        this.getProfileService.getProfilePic().subscribe((response)=>{
          const reader = new FileReader();
          reader.onload = () => {
            this.pictureUrlLink = reader.result as string;
          };
          reader.readAsDataURL(response);   
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
  
  createProfile(){

    this.submitted=true;

    if(!this.createProfileForm.invalid){

      //this.employer.profilePic=this.f['profilePicture'].value;
      this.employer.employerGender=this.f['gender'].value;
      this.employer.phno=this.f['phoneNumber'].value;
      this.employer.address=this.f['address'].value;
      this.employer.companyName=this.f['companyName'].value;

      this.createProfileService.createEmployerProfile(this.employer).subscribe(
        (res)=>{
          console.log(res.status);
          this.response=res.status;
        });

        alert( `Profile updated`);
        this.route.navigate(['/home']);

    }
    else{
      this.submitted=false;
      return ;
    }
  }

  updateProfile(){

    this.submitted=true;

    if(!this.createProfileForm.invalid){

      //this.employer.profilePic=this.f['profilePicture'].value;
      this.employer.employerGender=this.f['gender'].value;
      this.employer.phno=this.f['phoneNumber'].value;
      this.employer.address=this.f['address'].value;
      this.employer.companyName=this.f['companyName'].value;

      this.createProfileService.updateEmployerProfile(this.employer).subscribe(
        (res)=>{
          console.log(res.status);
          this.response=res.status.toString();
        });

        alert( `Profile updated`);
        this.route.navigate(['/home']);

    }
    else{
      this.submitted=false;
      return ;
    }
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
      this.createProfileService.uploadProfilePicture(this.pictureFile).then((res)=>{
        this.response=res;
      },
      (err)=>{
        alert(`Error occured`);
        console.log(err);
      })
    }
  }
}
