import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user = new User();

  constructor() { }

  ngOnInit() {
    const userdetails = sessionStorage.getItem('userdetails');
    if(userdetails){
      this.user = JSON.parse(userdetails);
    }
  }

}
