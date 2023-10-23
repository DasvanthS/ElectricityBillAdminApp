import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MonthlyBill } from 'src/app/classes/monthlyBill';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  customerId!:number;
  monthlyBills:MonthlyBill[] = [];

  constructor(private customerService:CustomerService, private route:ActivatedRoute) { }

  ngOnInit(): void {

    this.customerId = +this.route.snapshot.params['customerId'];
    console.log(this.customerId);
    
    this.customerService.getCustomerMonthlyBills(this.customerId)
    .subscribe(response => {
      this.monthlyBills = response;
      console.log(this.monthlyBills);
      
    },
    (error) => {
      console.log(error);
      
    }
    )
  }

}
