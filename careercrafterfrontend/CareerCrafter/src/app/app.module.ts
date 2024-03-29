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
import { ShowListingDetails } from './components/show-listing-details/show-listing-details.component';
import { PostListingComponent } from './components/post-listing/post-listing.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { CurrentUserReducer } from './store/reducers/current-user.reducer';
import { EditResumeComponent } from './components/edit-resume/edit-resume.component';
import { ApplyForJobComponent } from './components/apply-for-job/apply-for-job.component';
import { ListingManagementComponent } from './components/listing-management/listing-management.component';
import { ViewApplicationsComponent } from './components/view-applications/view-applications.component';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { DisplayResumeComponent } from './components/display-resume/display-resume.component';
import { ManageResumeDbComponent } from './components/manage-resume-db/manage-resume-db.component';
import { SeekerApplicationsComponent } from './components/seeker-applications/seeker-applications.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { BodyComponent } from './components/body/body.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { HomepageComponent } from './components/homepage/homepage.component'
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

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
    ShowListingDetails,
    PostListingComponent,
    NavBarComponent,
    EditResumeComponent,
    ApplyForJobComponent,
    ListingManagementComponent,
    ViewApplicationsComponent,
    DisplayResumeComponent,
    ManageResumeDbComponent,
    SeekerApplicationsComponent,
    SidenavComponent,
    BodyComponent,
    CarouselComponent,
    HomepageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    FormsModule,
    StoreModule.forRoot({currentUser: CurrentUserReducer}),
    HttpClientModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
