import { Component, OnInit } from '@angular/core';
import { Customer } from  '../../../classes/customer'; 
import { CustomerService } from '../../../services/customer.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  customer:Customer = new Customer();

  alertError: boolean = false;

  errorMessage:string = ""

  EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  constructor(private customerService:CustomerService, private router:Router, private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  saveCustomer(){
    this.customerService.addCustomer(this.customer)
    .subscribe(response => {
      console.log(response);
      this.snackbar.open("Customer added!", "", {
        duration:2000,
        verticalPosition: 'top'
      });
      setTimeout(() => {
        this.router.navigate(['/customers'])
      },500)
    },
    (error) => {
      console.error(error);
      this.alertError = true;
      this.errorMessage = error;
    }
    
    )
  }

  onSubmit(){
    console.log(this.customer);
    this.saveCustomer();
  }

}
