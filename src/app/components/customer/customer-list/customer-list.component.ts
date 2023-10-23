import { Component, OnInit, ViewChild } from '@angular/core';
import { Customer } from '../../../classes/customer';
import { CustomerService } from '../../../services/customer.service';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customerId!:number;
  
  customers: Customer[] = [];

  dataSource!: MatTableDataSource<any>;

  columns: string[] = ["CustomerID", "Name", "Email", "PhoneNumber", "Details"];

  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  constructor(private customerService: CustomerService, private router:Router, private snackBar:MatSnackBar) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getAllCustomers().subscribe({
      next:(response)=> {
        this.customers = response;
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginatior;
        this.dataSource.sort = this.sort;
        console.log(response);
      }
      });
  }

  viewCustomer(id:number){
    setTimeout(() => {
      this.router.navigate(['customers/getCustomerById',id]);
    },500);
  }

  filterData($event : any){
    this.dataSource.filter = $event.target.value;
  }

  onSubmit(){
    let hasCustomer = this.customers.some(customer => customer.customerId == this.customerId);
    if(hasCustomer){
      setTimeout(() => {
        this.router.navigate(['customers/getCustomerById',this.customerId]);
      },500);
      console.log(this.customerId);
    }
    else{
      this.snackBar.open("No customer found","" ,{
        duration:2000,
        verticalPosition:'top'
      });
    }  
  }

}
