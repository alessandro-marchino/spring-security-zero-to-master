import { Component, OnInit } from '@angular/core';
import { Notice } from 'src/app/model/notice.model.ts';
import { DashboardService } from 'src/app/services/dashboard/dashboard.service';

@Component({
  selector: 'app-notices',
  templateUrl: './notices.component.html',
  styleUrls: ['./notices.component.css']
})
export class NoticesComponent implements OnInit {

  notices: Notice[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.dashboardService.getNoticeDetails().subscribe(responseData => {
      this.notices = <any> responseData.body;
    });
  }

}
