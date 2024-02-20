import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employer-profile',
  templateUrl: './create-employer-profile.component.html',
  styleUrls: ['./create-employer-profile.component.css']
})
export class CreateEmployerProfileComponent {

  registerForm!:FormGroup;

  submitted=false;

  constructor(private formBuilder:FormBuilder,private route:Router){

  }

  createProfile(){

  }

  ngOnInit():void{
    this.registerForm=this.formBuilder.group({
      gender:['',[Validators.required]],
      phoneNumber:['',[Validators.required,Validators.minLength(10),Validators.maxLength(10),Validators.pattern('\\d{10}')]],
      address:['',[Validators.required]],
      companyName:['',[Validators.required]]

    });
  }

  get f(){
    return this.registerForm.controls;
  }
}
