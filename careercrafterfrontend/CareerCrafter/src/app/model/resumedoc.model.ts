// resume-doc.model.ts

export interface ResumeDoc {
    docId: string;
    name: string;
    type: string;
    data: Blob;
  }