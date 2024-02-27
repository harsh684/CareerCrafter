import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayResumeComponent } from './display-resume.component';

describe('DisplayResumeComponent', () => {
  let component: DisplayResumeComponent;
  let fixture: ComponentFixture<DisplayResumeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplayResumeComponent]
    });
    fixture = TestBed.createComponent(DisplayResumeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
