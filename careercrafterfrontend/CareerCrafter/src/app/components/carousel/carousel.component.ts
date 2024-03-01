import { Component } from '@angular/core';
import { Listing } from 'src/app/model/listing.model';
import { SearchJobsService } from 'src/app/services/SearchJobs/search-jobs.service';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent {

  listings:Listing[]=[]
  filteredList:Listing[]=[]

  constructor(private searchJobsService:SearchJobsService){}

  ngOnInit(){
    this.searchJobsService.getAvailableJobs()
    .subscribe((list)=>{
      this.listings=list;
      this.filteredList=list;
    });
  }
  

  



}
