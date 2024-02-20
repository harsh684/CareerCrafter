import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { LoginService } from 'src/app/services/LoginService/login.service';

@Component({
  selector: 'app-login-employer',
  templateUrl: './login-employer.component.html',
  styleUrls: ['./login-employer.component.css']
})
export class LoginEmployerComponent {

  loginService=inject(LoginService);

  constructor(private router:Router){}

  res!:string;

  login(userData:AuthInfo){
    if(localStorage.getItem("token")!=null){
      alert(`logged in`);
      this.router.navigate(['/home']);
      console.log(localStorage.getItem("token"));
    }else{
      alert(`Wrong credentials`);
    }
  }

}