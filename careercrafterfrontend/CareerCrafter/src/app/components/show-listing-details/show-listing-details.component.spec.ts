import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowListingDetails } from './show-listing-details.component';

describe('ShowListingDetails', () => {
  let component: ShowListingDetails;
  let fixture: ComponentFixture<ShowListingDetails>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowListingDetails]
    });
    fixture = TestBed.createComponent(ShowListingDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
