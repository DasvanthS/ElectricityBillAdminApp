import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Employee } from '../classes/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/employee';

  constructor(private http : HttpClient) { }

  validateLogin(id:number, email:string): Observable<Employee>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get<Employee>(`${this.baseUrl}/login?id=${id}&email=${email}`, {headers})
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    );
  }

  validateOtp(id:number, otp:string): Observable<Employee>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get<Employee>(`${this.baseUrl}/login-otp?id=${id}&otp=${otp}`, {headers})
    .pipe(
      catchError(error => {
        return throwError(error.error);
      })
    )
  }

}
