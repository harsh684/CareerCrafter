import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSeekerProfileComponent } from './create-seeker-profile.component';

describe('CreateSeekerProfileComponent', () => {
  let component: CreateSeekerProfileComponent;
  let fixture: ComponentFixture<CreateSeekerProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateSeekerProfileComponent]
    });
    fixture = TestBed.createComponent(CreateSeekerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
