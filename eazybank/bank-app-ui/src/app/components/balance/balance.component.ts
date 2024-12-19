import { Component, OnInit } from '@angular/core';
import { AccountTransactions, User } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { NgFor, CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';


@Component({
    selector: 'app-balance',
    templateUrl: './balance.component.html',
    styleUrls: ['./balance.component.css'],
    imports: [NgFor, RouterLink, CurrencyPipe]
})
export class BalanceComponent implements OnInit {

  transactions: AccountTransactions[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || '') as User;
    if(user){
      this.dashboardService.getAccountTransactions(user.customerId).subscribe(responseData => {
        this.transactions = responseData.body ?? [];
      });
    }
  }

}
