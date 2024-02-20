import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordStrengthComponentComponent } from './password-strength-component.component';

describe('PasswordStrengthComponentComponent', () => {
  let component: PasswordStrengthComponentComponent;
  let fixture: ComponentFixture<PasswordStrengthComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PasswordStrengthComponentComponent]
    });
    fixture = TestBed.createComponent(PasswordStrengthComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
