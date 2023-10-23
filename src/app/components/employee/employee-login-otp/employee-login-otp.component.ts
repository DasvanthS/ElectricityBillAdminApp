import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-login-otp',
  templateUrl: './employee-login-otp.component.html',
  styleUrls: ['./employee-login-otp.component.css']
})
export class EmployeeLoginOtpComponent implements OnInit {

  id!:number;
  otp!:string;

  errorAlert:boolean = false
  errorMessage:string = ""

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {

    this.id = +this.route.snapshot.params['id'];
    console.log(this.id);

    if(this.authService.isLoggedIn()){
        this.router.navigate(['dashboard'])
    }
    
  }

  onSubmit(){
    this.employeeService.validateOtp(this.id, this.otp)
    .subscribe(response => {
      console.log(response);
      this.authService.setToken('JWT');
      sessionStorage.setItem('id',this.id.toString());
      setTimeout(() => {
        this.router.navigate(['dashboard']);   
      },500)
    },
    error => {
      console.log(error);
      this.errorAlert = true;
      this.errorMessage = error;
      
    })
  }

}
