import { Resume } from "./resume.model";
export interface Applications {
    applicationId: number;
    companyName: string;
    profile: string;
    appliedDate: Date;
    status: string;
    coverLetter:String;
    resume: Resume;
  }