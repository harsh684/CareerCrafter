import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';
import { Employer } from 'src/app/model/employer.model';
import { CreateEmployerProfileService } from 'src/app/services/CreateEmployerProfile/create-employer-profile.service';
import { GetCurrentUserService } from 'src/app/services/GetCurrentUser/get-current-user.service';



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

  constructor(private formBuilder:FormBuilder,private route:Router, private store: Store<{currentUser: UserInfo}>){ }

  

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

      this.createProfileService.createEmployerProfile(this.employer).then((res)=>{
        this.response=res;
      });

      if(this.response!=""){
        alert( `Profile created`);
        this.route.navigate(['/home']);
      }else{
        alert('Some Error occured');
        this.route.navigate(['/create-employer-profile']);
      }

    }
    else{
      this.submitted=false;
      return ;
    }
  }
}
