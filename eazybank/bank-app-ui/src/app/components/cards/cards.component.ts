import { Component, OnInit } from '@angular/core';
import { Cards, User } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { NgFor, CurrencyPipe } from '@angular/common';
import { RouterLink } from '@angular/router';


@Component({
    selector: 'app-cards',
    templateUrl: './cards.component.html',
    styleUrls: ['./cards.component.css'],
    imports: [NgFor, RouterLink, CurrencyPipe]
})
export class CardsComponent implements OnInit {

  cards: Cards[] = [];
  currOutstandingAmt: number = 0;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    const user = JSON.parse(sessionStorage.getItem('userdetails') || '') as User;
    if(user){
      this.dashboardService.getCardsDetails(user.customerId).subscribe(responseData => {
        this.cards = responseData.body ?? [];
        this.currOutstandingAmt = this.cards.reduce((acc, card) => acc + card.availableAmount, 0);
      });
    }
  }

}
