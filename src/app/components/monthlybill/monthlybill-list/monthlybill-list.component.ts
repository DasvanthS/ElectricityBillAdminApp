import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MonthlyBill } from 'src/app/classes/monthlyBill';
import { MonthlybillService } from 'src/app/services/monthlybill.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import * as _ from 'lodash';


@Component({
  selector: 'app-monthlybill-list',
  templateUrl: './monthlybill-list.component.html',
  styleUrls: ['./monthlybill-list.component.css']
})
export class MonthlybillListComponent implements OnInit {

  monthlyBills: MonthlyBill[] = [];

  dataSource!: MatTableDataSource<any>;

  apiResponse:any = [];


  columns: string[] = ["BillID", "Month", "DueDate", "UnitConsumption", "Amount", "CustomerID", "Paid"];

  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  constructor(private monthlybillService: MonthlybillService, private router: Router) { }

  ngOnInit(): void {
    this.monthlybillService.getAllMonthlyBills().subscribe({
      next:(response) =>{
      this.monthlyBills = response;
      this.apiResponse = response;
      this.dataSource = new MatTableDataSource(response);
      this.dataSource.paginator = this.paginatior;
    }
    });


  }

  filterData($event : any){
    this.dataSource.filter = $event.target.value;
  }

  onChange(event: any) {
    const selectedValue: boolean = event.value === 'true'; 
  
    let filteredData = _.filter(this.apiResponse, (item) => {
      return item.paid === selectedValue;
    });
  
    this.dataSource = new MatTableDataSource(filteredData);
  }

}
