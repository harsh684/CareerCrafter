import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageResumeDbComponent } from './manage-resume-db.component';

describe('ManageResumeDbComponent', () => {
  let component: ManageResumeDbComponent;
  let fixture: ComponentFixture<ManageResumeDbComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageResumeDbComponent]
    });
    fixture = TestBed.createComponent(ManageResumeDbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
