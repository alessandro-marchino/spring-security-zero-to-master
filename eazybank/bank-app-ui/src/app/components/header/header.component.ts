import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css'],
    imports: [RouterLink, NgIf, RouterLinkActive]
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
