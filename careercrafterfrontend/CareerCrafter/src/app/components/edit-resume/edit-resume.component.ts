import { Component } from '@angular/core';
import { Resume } from 'src/app/model/resume.model';

@Component({
  selector: 'app-edit-resume',
  templateUrl: './edit-resume.component.html',
  styleUrls: ['./edit-resume.component.css']
})
export class EditResumeComponent {

  // craferResume:Resume={
  //   address: "J21 delhi updated",
  //   languages: [
  //     {
  //       languageId: 1,
  //       languageName: "Hindi"
  //     },
  //     {
  //       languageId: 2,
  //       languageName: "Telugu"
  //     }
  //   ],
  //   "skills": [
  //     {
  //       "skillId": 1,
  //       "skillName": "Java Full Stack"
  //     },
  //     {
  //       "skillId": 2,
  //       "skillName": "Python"
  //     }
  //   ],
  //   "referenceLinks": [
  //     {
  //       "linkId": 1,
  //       "link": "http://localhost8080/api/testing"
  //     },
  //     {
  //       "linkId": 2,
  //       "link": "http://localhost8080/api/deployment"
  //     }
  //   ],
  //   "accomplishments": [
  //     {
  //       "accomplishmentId": 1,
  //       "description": "accomplishment testing"
  //     },
  //     {
  //       "accomplishmentId": 2,
  //       "description": "accomplishment Development"
  //     }
  //   ],
  //   "experiences": [
  //     {
  //       "experienceId": 1,
  //       "companyName": "Hexaware",
  //       "startDate": "2000-10-10",
  //       "endDate": "2024-02-08",
  //       "salary": 30000,
  //       "description": "Work as a FSD"
  //     }
  //   ],
  //   "education": [
  //     {
  //       "educationId": 1,
  //       "collegeName": "TiT",
  //       "degree": "B-TECH",
  //       "specialization": "CSE",
  //       "startdate": "2019-05-05",
  //       "endDate": "2024-02-08",
  //       "percentage": 89
  //     }
  //   ],
  //   "projects": [
  //     {
  //       "projectId": 1,
  //       "title": "File Arranger Sytem",
  //       "description": "used to arrange file",
  //       "startDate": "2023-10-10",
  //       "endDate": "2023-11-11",
  //       "referenceLink": "localhost:8989/api/test",
  //       "hostedlink": "localhost:8787/api/test"
  //     }
  //   ],
  //   "certifications": [
  //     {
  //       "certificationId": 1,
  //       "title": "Java FSD",
  //       "description": "Detailed architecure",
  //       "startDate": "2023-10-10",
  //       "endDate": "2024-01-01"
  //     }
  //   ]
  // }
  languages: string[] = [];
  newLanguage: string = '';

  addLanguage() {
    if (this.newLanguage.trim() !== '') {
      this.languages.push(this.newLanguage);
      this.newLanguage = ''; // Clear input field
    }
  }

  onSubmit() {
    // Send languages array to component.ts
    console.log(this.languages);
    // Here you can further process the array or send it to the backend
  }

}
