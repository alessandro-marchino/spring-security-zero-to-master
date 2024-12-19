import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css'],
    imports: [RouterLink]
})
export class DashboardComponent implements OnInit {

  user = new User();

  constructor() { }

  ngOnInit() {
    const userdetails = sessionStorage.getItem('userdetails');
    if(userdetails){
      this.user = JSON.parse(userdetails);
    }
  }

}
