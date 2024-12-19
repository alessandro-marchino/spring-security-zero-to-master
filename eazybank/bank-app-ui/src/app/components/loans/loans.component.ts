import { Component, OnInit } from '@angular/core';
import { Loans, User } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { NgFor, CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-loans',
    templateUrl: './loans.component.html',
    styleUrls: ['./loans.component.css'],
    imports: [NgFor, RouterLink, CurrencyPipe]
})
export class LoansComponent implements OnInit {

  loans: Loans[] = [];
  currOutstandingBalance: number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || '') as User;
    if(user){
      this.dashboardService.getLoansDetails(user.customerId).subscribe(responseData => {
        this.loans = responseData.body ?? [];
        this.currOutstandingBalance = this.loans.reduce((acc, loan) => acc + loan.outstandingAmount, 0);
      });
    }
  }

}
