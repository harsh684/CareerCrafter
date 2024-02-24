import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserInfo } from 'src/app/model/UserInfo';
import { RegistrationService } from 'src/app/services/RegistrationServices/registration.service';

@Component({
  selector: 'app-register-seeker',
  templateUrl: './register-seeker.component.html',
  styleUrls: ['./register-seeker.component.css']
})
export class RegisterSeekerComponent {
  registerForm!:FormGroup;

  submitted=false;

  strongPassword=false;

  user:UserInfo={
    id:0,
    name:"",
    email:"",
    password:"",
    role:"",
    roleId:0
  };

  registrationService=inject(RegistrationService);

  constructor(private formBuilder:FormBuilder, private route:Router){

  }

  ngOnInit():void{
    this.registerForm=this.formBuilder.group({
      username:['',[Validators.required,Validators.pattern('^[a-zA-Z]+$')]],
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(3)]]
    });
  }

  get f(){
    return this.registerForm.controls;
  }

  onPasswordStrengthChanged(event: boolean) {
    this.strongPassword = event;
  }
  onSubmit():void{
    this.submitted=true;

    if(this.registerForm.valid){
      alert('Form submitted');
      this.user.name=this.f['username'].value;
      this.user.email=this.f['email'].value;
      this.user.password=this.f['password'].value;
      this.user.role='SEEKER';
      console.log(this.user);
      this.registrationService.register(this.user)
      alert("Account Created!");
      this.route.navigate(['/create-seeker-profile']);
    }else{
      this.submitted=false;
      alert("Error occured");
    }
  }
}
