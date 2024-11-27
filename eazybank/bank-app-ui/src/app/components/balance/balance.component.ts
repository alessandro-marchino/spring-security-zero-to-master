import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard/dashboard.service';
import { AccountTransactions } from 'src/app/model/account.transactions.model';
import { User } from 'src/app/model/user.model';


@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {

  transactions: AccountTransactions[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || "") as User;
    if(user){
      this.dashboardService.getAccountTransactions(user.customerId).subscribe(responseData => {
        this.transactions = responseData.body || [];
      });
    }
  }

}
