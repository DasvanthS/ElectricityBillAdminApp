import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeLoginOtpComponent } from './employee-login-otp.component';

describe('EmployeeLoginOtpComponent', () => {
  let component: EmployeeLoginOtpComponent;
  let fixture: ComponentFixture<EmployeeLoginOtpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeLoginOtpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeLoginOtpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
