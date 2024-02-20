import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { RegisterEmployerComponent } from './components/register-employer/register-employer.component'
import { PasswordStrengthComponentComponent } from './components/password-strength-component/password-strength-component.component';
import { LoginEmployerComponent } from './components/login-employer/login-employer.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterSeekerComponent } from './components/register-seeker/register-seeker.component';
import { LoginSeekerComponent } from './components/login-seeker/login-seeker.component';
import { CreateEmployerProfileComponent } from './components/create-employer-profile/create-employer-profile.component';
import { CreateSeekerProfileComponent } from './components/create-seeker-profile/create-seeker-profile.component';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { SearchJobsComponent } from './components/search-jobs/search-jobs.component';
import { ApplyForJobComponent } from './components/apply-for-job/apply-for-job.component';
import { PostListingComponent } from './components/post-listing/post-listing.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterEmployerComponent,
    PasswordStrengthComponentComponent,
    LoginEmployerComponent,
    HomeComponent,
    RegisterSeekerComponent,
    LoginSeekerComponent,
    CreateEmployerProfileComponent,
    CreateSeekerProfileComponent,
    SearchJobsComponent,
    ApplyForJobComponent,
    PostListingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
