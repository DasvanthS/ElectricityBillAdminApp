import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../classes/customer';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { MonthlyBill } from '../classes/monthlyBill';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/customers'; 

  constructor(private http: HttpClient) { }

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/getAllCustomers`);
  }

  addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.baseUrl}/addCustomer`, customer)
    .pipe(
      catchError(error => {
          return throwError(error.error);
      })
  );;
  }

  getCustomerId(customerId:number): Observable<Customer>{
    return this.http.get<Customer>(`${this.baseUrl}/getCustomerById/${customerId}`)
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }

  getCustomerMonthlyBills(customerId:number): Observable<MonthlyBill[]>{
    return this.http.get<MonthlyBill[]>(`${this.baseUrl}/getCustomerMonthlyBills/${customerId}`)
    .pipe(
      catchError(error => {
        return throwError(error.error);
      }) 
    )
  }

}
