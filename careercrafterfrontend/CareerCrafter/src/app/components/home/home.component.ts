import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { Store } from '@ngrx/store';
import { UserInfo } from 'src/app/model/UserInfo';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';
import { LoginService } from 'src/app/services/LoginService/login.service';

interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  isLoggedIn = false;
  currentRole:string|null = '';
  profileIImage = '';

  constructor(private getProfileService:GetSeekerProfileService,public loginService:LoginService,private route:Router,
    private store:Store<{currentUser:{currentUser: UserInfo}}>){
    // this.checkLoginStatus();
    this.currentRole = localStorage.getItem('currentRole');
    console.log(this.currentRole);
  }

  ngOnInit(){
    this.isLoggedIn=localStorage.getItem('loggedIn')!=null||this.loginService.session!=null;
    const parseStr = localStorage.getItem('currentUser');
      if(parseStr){
        const user=JSON.parse(parseStr);
        if(user.id!==0){
          this.isLoggedIn=true;
        }
      }
    if(this.loginService.session!=null){
      this.getProfileService.getProfilePic().subscribe(
        (response)=>{
          const reader = new FileReader();
          reader.onload = () => {
            this.profileIImage = reader.result as string;
          };
          reader.readAsDataURL(response);   
        },(err)=>{
          if(err.status===403){
            localStorage.clear();
            this.isLoggedIn=false;
            console.log(`data cleared`);
          }
        })
        console.log(this.profileIImage);
        }
    }
   
    login(){
      // this.isLoggedIn=true;
      this.route.navigate(['/login-seeker']);
    }

    logout(){
      this.isLoggedIn=false;
      this.loginService.logout();
      this.profileIImage = '';
      window.location.reload();
    }


  toggleMenu(): void {
    const itemElements = document.querySelectorAll('.item');
    itemElements.forEach(item => {
      item.classList.toggle('active');
    });
  }
}
