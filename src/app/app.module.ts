import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeLoginComponent } from './components/employee/employee-login/employee-login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component'
import { RouterModule } from '@angular/router';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { HttpClientModule } from '@angular/common/http';
import { CustomerCreateComponent } from './components/customer/customer-create/customer-create.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { HeaderComponent } from './components/header/header.component';
import { MonthlybillListComponent } from './components/monthlybill/monthlybill-list/monthlybill-list.component';
import { MonthlybillCreateComponent } from './components/monthlybill/monthlybill-create/monthlybill-create.component';
import { TransactionListComponent } from './components/transaction/transaction-list/transaction-list.component';
import { EmployeeLoginOtpComponent } from './components/employee/employee-login-otp/employee-login-otp.component';
import { TransactionDetailsComponent } from './components/transaction/transaction-details/transaction-details.component';
import { UploadCsvComponent } from './components/monthlybill/upload-csv/upload-csv.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatCardModule }  from "@angular/material/card"
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TransactionByCustomerComponent } from './components/transaction/transaction-by-customer/transaction-by-customer.component';
import { UpdateCashComponent } from './components/monthlybill/update-cash/update-cash.component';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard/admin-dashboard.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeLoginComponent,
    EmployeeLoginOtpComponent,
    PagenotfoundComponent,
    CustomerListComponent,
    CustomerCreateComponent,
    CustomerDetailsComponent,
    HeaderComponent,
    MonthlybillListComponent,
    MonthlybillCreateComponent,
    TransactionListComponent,
    TransactionDetailsComponent,
    UploadCsvComponent,
    TransactionByCustomerComponent,
    UpdateCashComponent,
    AdminDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatSnackBarModule,
    MatSelectModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
