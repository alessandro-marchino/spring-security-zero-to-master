import { Component, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { Contact } from 'src/app/model';
import { DashboardService } from 'src/app/services';
import { NgIf } from '@angular/common';


@Component({
    selector: 'app-contact',
    templateUrl: './contact.component.html',
    styleUrls: ['./contact.component.css'],
    imports: [NgIf, FormsModule]
})
export class ContactComponent implements OnInit {
  model = new Contact();
  contacts: Contact[] = [];

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() { }

  saveMessage(contactForm: NgForm) {
    this.dashboardService.saveMessage(this.model).subscribe(responseData => {
      this.contacts = responseData.body as Contact[];
      this.contacts.forEach(c => this.model = c);
      contactForm.resetForm();
    });
  }

}
