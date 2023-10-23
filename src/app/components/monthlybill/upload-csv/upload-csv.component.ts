import { Component, OnInit } from '@angular/core';
import { MonthlybillService } from 'src/app/services/monthlybill.service';

@Component({
  selector: 'app-upload-csv',
  templateUrl: './upload-csv.component.html',
  styleUrls: ['./upload-csv.component.css']
})
export class UploadCsvComponent implements OnInit {

  selectedFile:File | undefined;
  bugList:string[] = [];
  isUploaded:boolean = false

  constructor(private monthlyBillService: MonthlybillService) { }

  ngOnInit(): void {
  }

  onFileSelected(event:any):void{
    this.selectedFile = event.target.files[0];
  }

  onUpload():void{
    if(this.selectedFile){
      this.monthlyBillService.uploadCSV(this.selectedFile)
      .subscribe(response => {
        this.bugList = response;
        this.isUploaded = true;
      },
      error => {
        console.log(error);
        
      }
      )
    }
  }

}
