import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Transaction } from 'src/app/classes/transaction';
import { TransactionService } from 'src/app/services/transaction.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-transaction-by-customer',
  templateUrl: './transaction-by-customer.component.html',
  styleUrls: ['./transaction-by-customer.component.css']
})
export class TransactionByCustomerComponent implements OnInit {

  customerId!:number;
  transactions:Transaction[] = [];

  constructor(private transactionService: TransactionService, private route:ActivatedRoute, private router: Router, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.customerId = +this.route.snapshot.params['customerId'];

    this.transactionService.getCustomerTransactions(this.customerId)
    .subscribe(response => {
      this.transactions = response;
      console.log(this.transactions);
      if(this.transactions.length==0){
        this.snackBar.open("No Transactions found","",{
          duration:3000, 
          verticalPosition:'top'
        });
      }
    },
    (error) => {
      console.log(error);
    })

  }

  viewTransaction(id:number){
    setTimeout(() => {
      this.router.navigate(['transactions/getTransactionById', id]);
    },500);
  }

}
