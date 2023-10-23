import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeLoginComponent } from './components/employee/employee-login/employee-login.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { CustomerListComponent } from './components/customer/customer-list/customer-list.component';
import { CustomerCreateComponent } from './components/customer/customer-create/customer-create.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { MonthlybillListComponent } from './components/monthlybill/monthlybill-list/monthlybill-list.component';
import { MonthlybillCreateComponent } from './components/monthlybill/monthlybill-create/monthlybill-create.component';
import { TransactionListComponent } from './components/transaction/transaction-list/transaction-list.component';
import { EmployeeLoginOtpComponent } from './components/employee/employee-login-otp/employee-login-otp.component';
import { TransactionDetailsComponent } from './components/transaction/transaction-details/transaction-details.component';
import { UploadCsvComponent } from './components/monthlybill/upload-csv/upload-csv.component';
import { AuthGuardGuard } from './auth-guard.guard';
import { TransactionByCustomerComponent } from './components/transaction/transaction-by-customer/transaction-by-customer.component';
import { UpdateCashComponent } from './components/monthlybill/update-cash/update-cash.component';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard/admin-dashboard.component';

const routes: Routes = [
  {path : 'dashboard', component : AdminDashboardComponent, canActivate : [AuthGuardGuard]},
  {path : 'login', component : EmployeeLoginComponent},
  {path : 'login/otp/:id', component : EmployeeLoginOtpComponent},
  {path : 'customers', component : CustomerListComponent, canActivate : [AuthGuardGuard]},
  {path : 'customers/addCustomer', component : CustomerCreateComponent, canActivate : [AuthGuardGuard]},
  {path : 'customers/getCustomerById/:customerId', component : CustomerDetailsComponent, canActivate : [AuthGuardGuard]},
  {path : 'monthlyBills', component : MonthlybillListComponent, canActivate : [AuthGuardGuard]},
  {path : 'monthlyBills/addMonthlyBill', component : MonthlybillCreateComponent, canActivate : [AuthGuardGuard]},
  {path : 'monthlyBills/uploadCSV', component : UploadCsvComponent, canActivate : [AuthGuardGuard]},
  {path : 'monthlyBills/updateCash', component : UpdateCashComponent, canActivate : [AuthGuardGuard]},
  {path : 'transactions' ,component : TransactionListComponent, canActivate : [AuthGuardGuard]},
  {path : 'transactions/getTransactionById/:transactionId', component : TransactionDetailsComponent, canActivate : [AuthGuardGuard]},
  {path : 'transactions/getCustomerTransactions/:customerId', component : TransactionByCustomerComponent, canActivate : [AuthGuardGuard]},
  {path : '', redirectTo : '/dashboard', pathMatch : 'full'},
  {path : '**', component : PagenotfoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
