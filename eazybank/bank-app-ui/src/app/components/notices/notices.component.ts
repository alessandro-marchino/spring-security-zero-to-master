import { Component, OnInit } from '@angular/core';
import { Notice } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'app-notices',
    templateUrl: './notices.component.html',
    styleUrls: ['./notices.component.css'],
    imports: [NgFor, RouterLink]
})
export class NoticesComponent implements OnInit {

  notices: Notice[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.dashboardService.getNoticeDetails().subscribe(responseData => {
      this.notices = responseData.body ?? [];
    });
  }

}
