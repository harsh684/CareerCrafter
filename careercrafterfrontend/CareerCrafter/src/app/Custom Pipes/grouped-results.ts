import { Listing } from "../model/listing.model";

export interface GroupedResults {
    [key: string]: Listing[]; // Replace 'any' with the type of your objects
  }