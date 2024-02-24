// employer.model.ts
import { Listing } from "./listing.model";
import { ProfilePic } from "./profile.model";
export interface Employer {
  employerId: number;
  name: string;
  employerGender: string;
  email: string;
  phno: string;
  address: string;
  companyName: string;
  listings: Listing[];
  profilePic: ProfilePic;
}
