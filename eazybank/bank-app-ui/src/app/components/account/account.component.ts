import { Component, OnInit } from '@angular/core';
import { Account, User } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css'],
    imports: [RouterLink]
})
export class AccountComponent implements OnInit {
  user = new User();
  account = new Account();
  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    if(this.user){
      this.dashboardService.getAccountDetails(this.user.customerId).subscribe(responseData => {
        this.account = responseData.body ?? new Account();
      });
    }
  }

}
