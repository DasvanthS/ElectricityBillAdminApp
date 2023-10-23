import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { MonthlyBill } from '../classes/monthlyBill';
import { catchError } from 'rxjs/operators';
import { Transaction } from '../classes/transaction';

@Injectable({
  providedIn: 'root'
})
export class MonthlybillService {

  private baseUrl = 'http://localhost:8080/monthlyBills';

  constructor(private http: HttpClient) { }

  getAllMonthlyBills(): Observable<MonthlyBill[]>{
    return this.http.get<MonthlyBill[]>(`${this.baseUrl}/getAllMonthlyBills`);
  }

  addMonthlyBill(monthlyBill: MonthlyBill, customerId: number): Observable<MonthlyBill>{
    return this.http.post<MonthlyBill>(`${this.baseUrl}/addMonthlyBill?id=${customerId}`, monthlyBill)
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }

  uploadCSV(file:File): Observable<string[]>{
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post<string[]>(`${this.baseUrl}/uploadcsv`, formData);
  }

  updateCash(billId:number): Observable<Transaction>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.put<Transaction>(`${this.baseUrl}/updateCashPayment?billId=${billId}`, {headers})
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }

  getBillById(billId:number): Observable<MonthlyBill>{
    return this.http.get<MonthlyBill>(`${this.baseUrl}/getAllMonthlyBillById/${billId}`)
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }
}
