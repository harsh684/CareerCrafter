import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekerApplicationsComponent } from './seeker-applications.component';

describe('SeekerApplicationsComponent', () => {
  let component: SeekerApplicationsComponent;
  let fixture: ComponentFixture<SeekerApplicationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SeekerApplicationsComponent]
    });
    fixture = TestBed.createComponent(SeekerApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
