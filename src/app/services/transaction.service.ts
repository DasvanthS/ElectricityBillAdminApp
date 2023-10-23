import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Transaction } from '../classes/transaction';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) { }

  getAllTransactions(): Observable<Transaction[]>{
    return this.http.get<Transaction[]>(`${this.baseUrl}/getAllTransactions`);
  }

  getTransactionById(transactionId:number): Observable<Transaction>{
    return this.http.get<Transaction>(`${this.baseUrl}/getTransactionById/${transactionId}`)
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }

  getCustomerTransactions(customerId:number): Observable<Transaction[]>{
    return this.http.get<Transaction[]>(`${this.baseUrl}/getTransactionByCustomerId/${customerId}`)
    .pipe(
      catchError(error => {
      return throwError(error.error);
    }))
  }
}
