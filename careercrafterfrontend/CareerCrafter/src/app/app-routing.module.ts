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
import { seekerRoleGuard } from './guards/RoleGuards/seeker-role.guard';
import { employerRoleGuard } from './guards/RoleGuards/employer-role.guard';
import { SearchJobsComponent } from './components/search-jobs/search-jobs.component';
import { ApplyForJobComponent } from './components/apply-for-job/apply-for-job.component';
import { EditResumeComponent } from './components/edit-resume/edit-resume.component';

const routes: Routes = [
  {path: '',redirectTo: 'home',pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'register-employer',component: RegisterEmployerComponent},
  {path: 'register-seeker',component: RegisterSeekerComponent},
  {path: 'login-employer',component: LoginEmployerComponent},
  {path: 'login-seeker',component: LoginSeekerComponent},
  {path: 'create-seeker-profile',component: CreateSeekerProfileComponent,canActivate: [authGuard, seekerRoleGuard]},
  {path: 'create-employer-profile',component: CreateEmployerProfileComponent,canActivate: [authGuard, employerRoleGuard]},
  {path: 'search-jobs',component: SearchJobsComponent, canActivate: [authGuard, seekerRoleGuard]},
  {path: 'apply-for-job',component: ApplyForJobComponent,canActivate: [authGuard, seekerRoleGuard]},
  {path: 'edit-resume',component: EditResumeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
