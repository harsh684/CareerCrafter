import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AuthInfo } from 'src/app/model/AuthRequest';
import { UserInfo } from 'src/app/model/UserInfo';
import { LoginService } from 'src/app/services/LoginService/login.service';

@Component({
  selector: 'app-login-seeker',
  templateUrl: './login-seeker.component.html',
  styleUrls: ['./login-seeker.component.css']
})
export class LoginSeekerComponent {

  loginService=inject(LoginService);

  constructor(private router:Router){}

  async login(userData:AuthInfo){
    await this.loginService.loginUser(userData);
    if(localStorage.getItem("token")!==''){
      alert(`logged in`);
      // this.router.navigate(['/home']);
      this.router.navigate(['/search-jobs'],{ replaceUrl: true });
      console.log(localStorage.getItem("token"));
    }else{
      alert(`Wrong credentials`);
    }
  }
}
