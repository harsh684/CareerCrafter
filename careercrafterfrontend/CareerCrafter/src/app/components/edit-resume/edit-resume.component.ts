import { Component, inject } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReferenceLinks } from 'src/app/model/reference-links.model';
import { Observable, reduce } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo';
import { Education } from 'src/app/model/education.model';
import { Languages } from 'src/app/model/languages.model';
import { Resume } from 'src/app/model/resume.model';
import { Skills } from 'src/app/model/skills.model';
import { GetCurrentUserService } from 'src/app/services/GetCurrentUser/get-current-user.service';
import { GetSeekerProfileService } from 'src/app/services/GetSeekerProfile/get-seeker-profile.service';
import { GetResumeService } from 'src/app/services/GetSeekerProfile/get-resume.service';
import { Accomplishments } from 'src/app/model/accomplishments.model';
import { WorkExperience } from 'src/app/model/workexperience.model';
import { Project } from 'src/app/model/project.model';
import { Certification } from 'src/app/model/certification.model';

@Component({
  selector: 'app-edit-resume',
  templateUrl: './edit-resume.component.html',
  styleUrls: ['./edit-resume.component.css']
})
export class EditResumeComponent {
  formData!: FormGroup;
  experienceForm!:FormGroup;

  seekerName:string ='';
  seekerMail:string ='';

  submitted=false;
  isAddressValid=true;

resume:Resume={
  resumeId: 0,
  address: "",
  languages: [],
  skills:[],
  referenceLinks: [],
  accomplishments: [],
  experiences: [],
  education: [],
  projects: [],
  certifications: [],
  resumeFile: {
    docId: "",
    name: "",
    type: "",
      data: null,
  },
}

emptyProject: Project = {
  projectId: 0,
  title: '',
  description: '',
  startDate: new Date(),
  endDate: new Date(),
  referenceLink: '',
  hostedlink: ''
};

emptyExperience:WorkExperience = {
  experienceId: 0,
  companyName: '',
  startDate: new Date(),
  endDate: new Date(),
  salary: 0,
  description: ''
};

  languagesList:string[] = [];
  skillsList:string[] = [];
  referenceLinksList:string[]=[];
  accomplishmentsList:string[]=[];
  experiencesList:WorkExperience[]=[];
  educationsList:Education[]=[];
  projectsList:Project[]=[];
  certificationsList:Certification[]=[];

  constructor(private formBuilder: FormBuilder,private editresumeservice:GetResumeService,
    private router:Router) { 

  }

  ngOnInit(): void {
    this.editresumeservice.getCrafterResume().subscribe(
      res=>{
        this.resume = res;
        console.log(this.resume);
        for(let skill of this.resume.skills){
          this.skillsList.push(skill.skillName);
        }
        for(let language of this.resume.languages){
          this.languagesList.push(language.languageName);
        }
        for(let referenceLinks of this.resume.referenceLinks){
          this.referenceLinksList.push(referenceLinks.link);
        }
        for(let accomplishments of this.resume.accomplishments){
          this.accomplishmentsList.push(accomplishments.description);
        }
        this.experiencesList = this.resume.experiences;
        this.educationsList = this.resume.education;
        this.projectsList = this.resume.projects;
        this.certificationsList = this.resume.certifications;
    
        this.editresumeservice.getSeekerNameByResumeId(this.resume.resumeId).subscribe(
          res=>{
            const temp  = res.split(',');
            this.seekerName=temp[0];
            this.seekerMail=temp[1];
          },
          err=>{
            if(err.status === 200){
              const temp  = err.error.text.split(',');
              this.seekerName=temp[0];
              this.seekerMail=temp[1];
            }
          }
        )

      },
      (err)=>{
        if(err.status === 403){
          this.router.navigate(['/login-seeker']);
        }
      }
    );


    this.formData = this.formBuilder.group({
      address:['',Validators.required],
      languages: [],
      skills:[''] ,
      referenceLinks: [],
      accomplishments:[],
      experiences: [],
      education: [],
      projects: [],
      certifications: [],
      projectTitle: [''],
      projectStartDate: [''],
      projectEndDate: [''],
      projectDescription: [''],
      projectReferenceLink: [''],
      projectHostedLink: [''],
      experienceCompanyName: [''],
      experienceStartDate: [''],
      experienceEndDate: [''],
      experienceSalary: [''],
      experienceDescription: [''],
      educationCollegeName: [''],
      educationDegree: [''],
      educationSpecialization: [''],
      educationStartDate: [''],
      educationEndDate: [''],
      educationPercentage: [''],
      certificationTitle: [''],
      certificationDescription: [''],
      certificationStartDate: [''],
      certificationEndDate: [''],
    });

    this.formData.patchValue({
      address:this.resume.address,
    })
    }

    get f(){
      return this.formData.controls;
    }
    onSubmit(){
     console.log(this.resume);
     if(this.f['address'].value!==''||this.f['address'].value!==null){
      this.resume=this.f['address'].value;
     }
     if(this.resume.address !== ""||this.resume.address !== null){
      this.isAddressValid = true;
      this.editresumeservice.editresume(this.resume).subscribe(
        (res)=>{
          alert('resume updated successfully '+res);
        },
        (err)=>{
          if(err.status === 403){
            alert('Session Expired');
            this.router.navigate(['/login-seeker']);
          }else if(err.status === 200){
            alert('resume updated successfully');
          }
        }
      );
     }else{
      this.isAddressValid = false;
      return;
     }
    }

    addSkill(){
      const newSkill = this.f['skills'].value.trim();
      if(newSkill && !this.skillsList.includes(newSkill)) {
        // this.resume.skills.push({skillId: 0,skillName:newSkill});
        this.skillsList.push(newSkill);
        this.resume.skills.push({skillId: 0,skillName: newSkill});
        this.f['skills'].setValue('');
        console.log(this.skillsList);
      }else if(this.skillsList.includes(newSkill)){
        alert('Skill already exists');
        this.f['skills'].setValue('');
      }
    }

    removeSkill(skillName:string){
      const index = this.skillsList.indexOf(skillName);
      if (index >= 0) {
        this.skillsList.splice(index, 1);
        this.resume.skills = this.resume.skills.filter(skill => skill.skillName !== skillName);
      }
      console.log(this.resume.skills);
    }

    addlanguage(){
      const newLanguage = this.f['languages'].value.trim();
      if(newLanguage && !this.languagesList.includes(newLanguage)) {
        // this.resume.skills.push({skillId: 0,skillName:newSkill});
        this.languagesList.push(newLanguage);
        this.resume.languages.push({languageId: 0,languageName: newLanguage});
        this.f['languages'].setValue('');
        console.log(this.skillsList);
      }else if(this.languagesList.includes(newLanguage)){
        alert('language already exists');
        this.f['languages'].setValue('');
      }
    }

    removeLanguage(language:string){
      const index = this.languagesList.indexOf(language);
      if (index >= 0) {
        this.languagesList.splice(index, 1);
        this.resume.languages = this.resume.languages.filter(languageObj => languageObj.languageName !== language);
      }
    }

    addReferenceLinks(){
      const newLink = this.f['referenceLinks'].value.trim();
      if(newLink && !this.referenceLinksList.includes(newLink)) {
        // this.resume.skills.push({skillId: 0,skillName:newSkill});
        this.referenceLinksList.push(newLink);
        this.resume.referenceLinks.push({linkId: 0,link: newLink});
        this.f['referenceLinks'].setValue('');
        console.log(this.referenceLinksList);
      }else if(this.referenceLinksList.includes(newLink)){
        alert('Link already exists');
        this.f['referenceLinks'].setValue('');
      }
    }

    removeLink(link:string){
      const index = this.referenceLinksList.indexOf(link);
      if (index >= 0) {
        this.referenceLinksList.splice(index, 1);
        this.resume.referenceLinks = this.resume.referenceLinks.filter(linkObj => linkObj.link !== link);
      }
    }

    addAccomplishment(){
      const newAccomplishment = this.f['accomplishments'].value.trim();
      if(newAccomplishment && !this.accomplishmentsList.includes(newAccomplishment)) {
        // this.resume.skills.push({skillId: 0,skillName:newSkill});
        this.accomplishmentsList.push(newAccomplishment);
        this.resume.accomplishments.push({accomplishmentId: 0,description: newAccomplishment});
        this.f['accomplishments'].setValue('');
        console.log(this.accomplishmentsList);
      }else if(this.accomplishmentsList.includes(newAccomplishment)){
        alert('Accomplishment already exists');
        this.f['accomplishments'].setValue('');
      }
    }

    removeAccomplishment(accomplishment:string){
      const index = this.accomplishmentsList.indexOf(accomplishment);
      if (index >= 0) {
        this.accomplishmentsList.splice(index, 1);
        this.resume.accomplishments = this.resume.accomplishments.filter(accomplishmentObj => accomplishmentObj.description !== accomplishment);
      }
    }

    addProject(){
      let newProject:Project = {
        projectId:0,
        title: this.f['projectTitle'].value,
        startDate: this.f['projectStartDate'].value,
        endDate: this.f['projectEndDate'].value,
        description: this.f['projectDescription'].value,
        referenceLink: this.f['projectReferenceLink'].value,
        hostedlink: this.f['projectHostedLink'].value
      };
    
      // Push the new project object to the projects list
      this.resume.projects.push(newProject);
    
      // Update projectsList
      this.projectsList = this.resume.projects;

      this.f['projectTitle'].setValue('');
      this.f['projectStartDate'].setValue('');
      this.f['projectEndDate'].setValue('');
      this.f['projectDescription'].setValue('');
      this.f['projectReferenceLink'].setValue('');
      this.f['projectHostedLink'].setValue('');

    }

    removeProject(projectTitle:string){
      const index = this.resume.projects.findIndex(project => project.title === projectTitle);
      if (index !== -1) {
        this.resume.projects.splice(index, 1);
      }
    }

    addExperience(){
      let newExperience:WorkExperience = {
        experienceId: 0,
        companyName: this.f['experienceCompanyName'].value,
        startDate: this.f['experienceStartDate'].value,
        endDate: this.f['experienceEndDate'].value,
        salary: this.f['experienceSalary'].value,
        description: this.f['experienceDescription'].value
      };
    
      // Push the new experience object to the experiences list
      this.resume.experiences.push(newExperience);
    
      // Update experiencesList
      this.experiencesList = this.resume.experiences;
      console.log(this.resume.experiences);
      console.log(this.experiencesList);

      this.f['experienceCompanyName'].setValue('');
      this.f['experienceStartDate'].setValue('');
      this.f['experienceEndDate'].setValue('');
      this.f['experienceSalary'].setValue('');
      this.f['experienceDescription'].setValue('');

      
    }

    removeExperience(experience:WorkExperience){
      this.resume.experiences = this.resume.experiences.filter(exp => exp!== experience);
      this.experiencesList = this.resume.experiences;
    }

    addEducation(){
      let emptyEducation:Education = {
        educationId: 0,
        collegeName: this.f['educationCollegeName'].value,
        degree: this.f['educationDegree'].value,
        specialization: this.f['educationSpecialization'].value,
        startdate: this.f['educationStartDate'].value,
        endDate: this.f['educationEndDate'].value,
        percentage: this.f['educationPercentage'].value
      };

      this.resume.education.push(emptyEducation);

      this.f['educationCollegeName'].setValue('');
      this.f['educationDegree'].setValue('');
      this.f['educationSpecialization'].setValue('');
      this.f['educationStartDate'].setValue('');
      this.f['educationEndDate'].setValue('');
      this.f['educationPercentage'].setValue('');

    }

    removeEducation(education:Education){
      this.resume.education = this.resume.education.filter(edu => edu!== education);
      this.educationsList = this.resume.education;
    }

    addCertification(){
      let emptyCertification:Certification = {
        certificationId: 0,
        title: this.f['certificationTitle'].value,
        description: this.f['certificationDescription'].value,
        startDate: this.f['certificationStartDate'].value,
        endDate: this.f['certificationEndDate'].value
      };

      this.resume.certifications.push(emptyCertification);

      this.f['certificationTitle'].setValue('');
      this.f['certificationDescription'].setValue('');
      this.f['certificationStartDate'].setValue('');
      this.f['certificationEndDate'].setValue('');
    }

    removeCertifiation(certification:Certification){
      this.resume.certifications = this.resume.certifications.filter(cert => cert!== certification);
      this.certificationsList = this.resume.certifications;
    }

}
