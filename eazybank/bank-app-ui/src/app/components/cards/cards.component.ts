import { Component, OnInit } from '@angular/core';
import { Cards } from 'src/app/model/cards.model';
import { DashboardService } from '../../services/dashboard/dashboard.service';


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
    const user = JSON.parse(sessionStorage.getItem('userdetails') || "");
    if(user){
      this.dashboardService.getCardsDetails(user.id).subscribe(responseData => {
        this.cards = responseData.body || [];
        this.cards.forEach((card: Cards) => {
          this.currOutstandingAmt += card.availableAmount;
        });
      });
    }
  }

}
