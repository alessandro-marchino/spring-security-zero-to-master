import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
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