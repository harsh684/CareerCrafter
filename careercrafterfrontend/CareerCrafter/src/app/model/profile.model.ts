// profile-pic.model.ts

export interface ProfilePic {
    pictureId: string;
    name: string;
    type: string;
    data: Blob | null;
    role: string;
  }
  