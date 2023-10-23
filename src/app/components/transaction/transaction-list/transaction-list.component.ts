import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/classes/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {

  transactions: Transaction[] = [];

  customerId!:number;

  customerIDFilter: string = '';

  dataSource!: MatTableDataSource<any>;

  columns: string[] = ["TransactionID", "PaymentDate", "Amount", "PaymentMethod", "CustomerID", "ViewDetails"];

  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  constructor(private transactionService: TransactionService, private router: Router, private snakeBar: MatSnackBar) { 

  }

  ngOnInit(): void {
    this.getTransactions();

  }

  getTransactions(): void{
    this.transactionService.getAllTransactions().subscribe({
      next:(response) => {
      this.transactions = response;
      this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginatior;
        this.dataSource.sort = this.sort;
        console.log(response);
    }
    });
  }

  viewTransaction(id:number){   
      setTimeout(() => {
        this.router.navigate(['transactions/getTransactionById', id]);
      },500); 
  }

  onSubmit(){
    let hasCustomer = this.transactions.some(transaction => transaction.customer.customerId == this.customerId);
    if(hasCustomer){
      setTimeout(() => {
        this.router.navigate(['transactions/getCustomerTransactions',this.customerId]);
      },500);
      console.log(this.customerId);
    }
    else{
      this.snakeBar.open("No customer found","" ,{
        duration:2000,
        verticalPosition:'top'
      });
    }  
  }

  // filterData($event : any){
  //   this.dataSource.filter = $event.target.value;
  // }


}
