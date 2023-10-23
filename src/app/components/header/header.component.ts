import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  id!:any;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.id = sessionStorage.getItem('id');
  }

  onLogout(): void{
    this.authService.logout();
  }

  customers(){
    setTimeout(() => {
      this.router.navigate(['customers']);
    },500);
  }

  monthlyBills(){
    setTimeout(() => {
      this.router.navigate(['monthlyBills']);
    },500);
  }

  transactions(){
    setTimeout(() => {
      this.router.navigate(['transactions']);
    },500);
  }

  dashboard(){
    setTimeout(() => {
      this.router.navigate(['dashboard']);
    },500);
  }

}
