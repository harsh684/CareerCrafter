// listing.model.ts

import { Skills } from "./skills.model";
import { Applications } from "./applications.model";
import { Employer } from "./employer.model";
export interface Listing {
  listingId: number;
  profile: string;
  companyName: string;
  department: string;
  location: string;
  experienceReqFrom: number;
  experienceReqTo: number;
  salary: number;
  postDate: Date;
  listingStatus: string;
  reqSkills: Skills[];
  jd: string;
  benefitsProvided: string;
  applications: Applications[];
  employer: Employer;
}
