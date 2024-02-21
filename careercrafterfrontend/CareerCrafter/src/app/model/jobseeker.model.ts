// job-seeker.model.ts

import { Resume } from "./resume.model";
import { Applications } from "./applications.model";
import { ProfilePic } from "./profile.model";
export interface JobSeeker {
  seekerId: number;
  seekerName: string;
  seekerGender: string;
  tagline: string;
  email: string;
  summary: string;
  dateOfBirth: Date;
  phoneNumber: string;
  address: string;
  country: string;
  currentSalary: number;
  resume: Resume;
  applications: Applications[];
  profilePic: ProfilePic;
}
