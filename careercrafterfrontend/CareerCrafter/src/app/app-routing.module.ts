import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterEmployerComponent } from './components/register-employer/register-employer.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginEmployerComponent } from './components/login-employer/login-employer.component';
import { RegisterSeekerComponent } from './components/register-seeker/register-seeker.component';
import { LoginSeekerComponent } from './components/login-seeker/login-seeker.component';
import { CreateSeekerProfileComponent } from './components/create-seeker-profile/create-seeker-profile.component';
import { CreateEmployerProfileComponent } from './components/create-employer-profile/create-employer-profile.component';
import { authGuard } from './guards/AuthGuards/auth.guard';
import { seekerRoleGuardGuard } from './guards/RoleGuards/seeker-role-guard.guard';
import { SearchJobsComponent } from './components/search-jobs/search-jobs.component';
import { ShowListingDetails } from './components/show-listing-details/show-listing-details.component';
import { EditResumeComponent } from './components/edit-resume/edit-resume.component';
import { ApplyForJobComponent } from './components/apply-for-job/apply-for-job.component';
import { ListingManagementComponent } from './components/listing-management/listing-management.component';
import { ViewApplicationsComponent } from './components/view-applications/view-applications.component';
import { EmployerRoleGuardGuard } from './guards/RoleGuards/employer-role-guard.guard';
import { DisplayResumeComponent } from './components/display-resume/display-resume.component';

const routes: Routes = [
  {path: '',redirectTo: 'home',pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'register-employer',component: RegisterEmployerComponent},
  {path: 'register-seeker',component: RegisterSeekerComponent},
  {path: 'login-employer',component: LoginEmployerComponent},
  {path: 'login-seeker',component: LoginSeekerComponent},
  {path: 'create-seeker-profile',component: CreateSeekerProfileComponent,canActivate: [authGuard, seekerRoleGuardGuard]},
  {path: 'create-employer-profile',component: CreateEmployerProfileComponent,canActivate: [authGuard, EmployerRoleGuardGuard]},
  {path: 'search-jobs',component: SearchJobsComponent, canActivate: [authGuard, seekerRoleGuardGuard]},
  {path: 'show-listing-details',component: ShowListingDetails,canActivate: [authGuard, seekerRoleGuardGuard]},
  {path: 'apply-for-job/:listingId',component: ApplyForJobComponent,canActivate: [authGuard,seekerRoleGuardGuard]},
  {path: 'edit-resume',component: EditResumeComponent,canActivate: [authGuard,seekerRoleGuardGuard]},
  {path: 'manage-listings',component: ListingManagementComponent,canActivate: [authGuard,EmployerRoleGuardGuard]},
  {path: 'view-applications',component: ViewApplicationsComponent,canActivate: [authGuard,EmployerRoleGuardGuard]},
  {path: 'display-resume',component: DisplayResumeComponent,canActivate: [authGuard,EmployerRoleGuardGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
