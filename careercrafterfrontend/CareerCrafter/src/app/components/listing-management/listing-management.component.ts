import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Applications } from 'src/app/model/applications.model';
import { Listing } from 'src/app/model/listing.model';
import { ListingService } from 'src/app/services/Listings/listing.service';

@Component({
  selector: 'app-listing-management',
  templateUrl: './listing-management.component.html',
  styleUrls: ['./listing-management.component.css']
})
export class ListingManagementComponent {

  listingList:Listing[]=[]
  allListingList:Listing[]=[];
  filteredApplication:Applications[]=[];
  filterProfile='';

  constructor(private listingService:ListingService,private route:Router){}

  ngOnInit():void{
    //this.listingService.getMYListings();
    this.listingService.getMYListings().subscribe(listings=>{
      console.log(listings);
      this.listingList=listings;
      this.allListingList=listings;
    },
    (error)=>{
      if(error.status === 403){
        localStorage.clear();
        this.route.navigate(['/login-employer']);
      }
      console.log(error);
    });
  }

  filter(){
    this.listingList=this.allListingList;
      if (this.filterProfile === '' || this.filterProfile.toLowerCase() === 'all') {
        // If no profile is selected, assign all applications to filteredApplications
        this.listingList = this.allListingList;
      } else {
        // If a profile is selected, filter applications by profile and assign the filtered list
        this.listingList = this.allListingList.filter(app => app.profile.toLowerCase().includes(this.filterProfile.toLowerCase()));
      }
    
  }
  checkApplications(listingId:number){
    let applicationsForListing:Applications[]=[];
    this.listingService.viewApplicationsForListing(listingId).subscribe(
      (applications=>{
        applicationsForListing = applications;
        const encodedData=encodeURI(JSON.stringify(applications));
        this.route.navigate(['/view-applications'],{
        queryParams:{
          applications: JSON.stringify(applicationsForListing)
        }
    });
      })
    )
  }

  closeListing(listingId:number){
    
    this.listingList = this.listingList.map(listing => {
      if (listing.listingId === listingId) {
        listing.listingStatus = listing.listingStatus === 'Open' ? 'Closed' : 'Open';
        alert(`Listing ${listing.listingStatus}`);
        this.listingService.changeListingStatus(listingId,listing.listingStatus)
        .subscribe(
          (res)=>{
            console.log(res);
          }
        )
      }
      return listing;
    });

  }

}
