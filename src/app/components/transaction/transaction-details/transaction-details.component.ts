import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MonthlyBill } from 'src/app/classes/monthlyBill';
import { Transaction } from 'src/app/classes/transaction';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.css']
})
export class TransactionDetailsComponent implements OnInit {

  transactionId!:number;
  transaction!:Transaction;

  constructor(private transactionService: TransactionService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.transactionId = +this.route.snapshot.params['transactionId'];
    console.log(this.transactionId);

    this.transactionService.getTransactionById(this.transactionId)
    .subscribe(response => {
      this.transaction = response;
      console.log(this.transaction);
      
    },
    error => {
      console.log(error);
      
    })
    
  }

}
