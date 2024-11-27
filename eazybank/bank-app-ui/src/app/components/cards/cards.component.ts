import { Component, OnInit } from '@angular/core';
import { Cards } from 'src/app/model/cards.model';
import { DashboardService } from '../../services/dashboard/dashboard.service';
import { User } from 'src/app/model/user.model';


@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {

  cards: Cards[] = [];
  currOutstandingAmt: number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || "") as User;
    if(user){
      this.dashboardService.getCardsDetails(user.customerId).subscribe(responseData => {
        this.cards = responseData.body || [];
        this.cards.forEach((card: Cards) => {
          this.currOutstandingAmt += card.availableAmount;
        });
      });
    }
  }

}
