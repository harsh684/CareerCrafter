import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employer } from 'src/app/model/employer.model';
import { Listing } from 'src/app/model/listing.model';
import { Skills } from 'src/app/model/skills.model';
import { ListingService } from 'src/app/services/Listings/listing.service';

@Component({
  selector: 'app-post-listing',
  templateUrl: './post-listing.component.html',
  styleUrls: ['./post-listing.component.css']
})
export class PostListingComponent {

  employerTemp!:Employer;

  listing:Listing={
    listingId: 0,
    profile: '',
    companyName: '',
    department: '',
    location: '',
    experienceReqFrom: 0,
    experienceReqTo: 0,
    salary: 0,
    postDate: new Date(),
    listingStatus: 'Open',
    reqSkills: [],
    jd: '',
    benefitsProvided: '',
    applications: [],
    employer: this.employerTemp
  }

  jobForm!: FormGroup;
  addedSkills: Skills[] = [];

  constructor(private formBuilder: FormBuilder,private listingService:ListingService) { }

  ngOnInit(): void {
    this.jobForm = this.formBuilder.group({
      profile: ['', Validators.required],
      companyName: ['', Validators.required],
      department: ['', Validators.required],
      location: ['', Validators.required],
      benefitsProvided: ['',Validators.required],
      jobDescription: ['',Validators.required],
      experienceReqFrom: [0, Validators.required],
      experienceReqTo: [0, Validators.required],
      salary: [0, Validators.required],
      newSkill: ['']
    });
  }

  get f(){
    return this.jobForm.controls;
  }
  addSkill() {
    const newSkill = this.f['newSkill'].value.trim();
    if (newSkill && !this.addedSkills.includes(newSkill)) {
      this.addedSkills.push(newSkill);
      this.listing.reqSkills = this.addedSkills;
      this.f['newSkill'].setValue('');
      console.log(this.listing.reqSkills)
    }
  }

  removeSkill(skill: Skills) {
    const index = this.addedSkills.indexOf(skill);
    if (index !== -1) {
      this.addedSkills.splice(index, 1);
    }
  }

  submitForm() {
    // Submit form data including addedSkills
    this.listing.benefitsProvided = this.f['benefitsProvided'].value;
    this.listing.profile = this.f['profile'].value;
    this.listing.department = this.f['department'].value;
    this.listing.location = this.f['location'].value;
    this.listing.companyName= this.f['companyName'].value;
    this.listing.experienceReqFrom = this.f['experienceReqFrom'].value;
    this.listing.experienceReqTo = this.f['experienceReqTo'].value;
    this.listing.salary = this.f['salary'].value;
    this.listing.jd = this.f['jobDescription'].value;
    
    console.log(this.listing);

    this.listingService.postListing(this.listing).subscribe(
      (res)=>{
        console.log(res);
      },
      (err)=>{
        if(err.status === 200){
          alert(`listing posted`);
        }else{
          alert('Could not post listing');
        }
      }
    )

  }
}

