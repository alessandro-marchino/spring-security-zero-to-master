import { Component, OnInit } from '@angular/core';
import { Loans } from 'src/app/model/loans.model';
import { DashboardService } from '../../services/dashboard/dashboard.service';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.css']
})
export class LoansComponent implements OnInit {

  loans: Loans[] = [];
  currOutstandingBalance: number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || "") as User;
    if(user){
      this.dashboardService.getLoansDetails(user.customerId).subscribe(responseData => {
        this.loans = responseData.body || [];
        this.loans.forEach((loan: Loans) => {
          this.currOutstandingBalance += loan.outstandingAmount;
        });
      });
    }
  }

}
