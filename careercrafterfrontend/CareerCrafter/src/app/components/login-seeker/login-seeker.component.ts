import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { LoginService } from 'src/app/services/LoginService/login.service';

@Component({
  selector: 'app-login-seeker',
  templateUrl: './login-seeker.component.html',
  styleUrls: ['./login-seeker.component.css']
})
export class LoginSeekerComponent {

  loginService=inject(LoginService);

  constructor(private router:Router){}


  login(userData:AuthInfo){
    this.loginService.loginUser(userData);
    if(localStorage.getItem("token")!=null){
      alert(`logged in`);
      this.router.navigate(['/home']);
      console.log(localStorage.getItem("token"));
    }else{
      alert(`Wrong credentials`);
    }
  }
}
