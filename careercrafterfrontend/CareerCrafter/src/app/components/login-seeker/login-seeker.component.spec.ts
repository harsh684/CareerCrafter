import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginSeekerComponent } from './login-seeker.component';

describe('LoginSeekerComponent', () => {
  let component: LoginSeekerComponent;
  let fixture: ComponentFixture<LoginSeekerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LoginSeekerComponent]
    });
    fixture = TestBed.createComponent(LoginSeekerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
