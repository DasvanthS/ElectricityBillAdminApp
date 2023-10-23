import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Dashboard } from '../classes/dashboard';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = "http://localhost:8080/dashboard";

  constructor(private http: HttpClient) { }


  getDetails(): Observable<Dashboard> {
    return this.http.get<Dashboard>(`${this.baseUrl}/getDetails`);
  }
}
