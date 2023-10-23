import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/classes/transaction';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  transactions: Transaction[] = [];

  constructor(private transactionService: TransactionService, private router: Router) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(): void{
    this.transactionService.getAllTransactions().subscribe({
      next:(response) => {
      this.transactions = response;
        console.log(response);
    }
    });
  }

  onCustomers(){
    setTimeout(() => {
      this.router.navigate(['customers']);
    },500);
  }

  onMonthlyBills(){
    setTimeout(() => {
      this.router.navigate(['monthlyBills']);
    },500);
  }

  onTransactions(){
    setTimeout(() => {
      this.router.navigate(['transactions']);
    },500);
  }
}
