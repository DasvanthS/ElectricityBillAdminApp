import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit {

  employeeId!:number;
  email!:string

  errorAlert:boolean = false;

  errorMessage = "";

  EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  constructor(private employeeService:EmployeeService, private router:Router, private authService: AuthService) { }

  ngOnInit(): void {
    if(this.authService.isLoggedIn()){
        this.router.navigate(['dashboard'])
    }
  }

  onLogin(){
    this.employeeService.validateLogin(this.employeeId, this.email)
    .subscribe(response => {
      console.log(response);
      alert("Otp is " + response.otp);
        this.router.navigate(['/login/otp', this.employeeId]);
    },
    error => {
      console.log(error);
      this.errorAlert = true;
      this.errorMessage = error;   
    })
  }

}
