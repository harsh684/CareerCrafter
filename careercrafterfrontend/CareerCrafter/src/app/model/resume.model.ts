// resume.model.ts
import { Languages } from "./languages.model";
import { Skills } from "./skills.model";
import { Project } from "./project.model";
import { ReferenceLinks } from "./reference-links.model";
import { Education } from "./education.model";
import { Accomplishments } from "./accomplishments.model";
import { WorkExperience } from "./workexperience.model";
import { Certification } from "./certification.model";
import { ResumeDoc } from "./resumedoc.model";
export interface Resume {
  resumeId: number;
  address: string;
  languages: Languages[];
  skills: Skills[];
  referenceLinks: ReferenceLinks[];
  accomplishments: Accomplishments[];
  experiences: WorkExperience[];
  education: Education[];
  projects: Project[];
  certifications: Certification[];
  resumeFile: ResumeDoc;
}
