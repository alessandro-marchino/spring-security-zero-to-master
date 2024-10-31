import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AppConstants } from "../../constants/app.constants";
import { environment } from '../../../environments/environment';
import { Contact } from '../../model/contact.model';
import { Loans } from 'src/app/model/loans.model';
import { Observable } from 'rxjs';
import { Cards } from 'src/app/model/cards.model';
import { AccountTransactions } from 'src/app/model/account.transactions.model';
import { Account } from 'src/app/model/account.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http:HttpClient) {}

  getAccountDetails(id: number){
    return this.http.get<Account>(`${environment.rooturl}${AppConstants.ACCOUNT_API_URL}?id=${id}`, { observe: 'response', withCredentials: true });
  }

  getAccountTransactions(id: number){
    return this.http.get<AccountTransactions[]>(`${environment.rooturl}${AppConstants.BALANCE_API_URL}?id=${id}`, { observe: 'response', withCredentials: true });
  }

  getLoansDetails(id: number) {
    return this.http.get<Loans[]>(`${environment.rooturl}${AppConstants.LOANS_API_URL}?id=${id}`, { observe: 'response', withCredentials: true });
  }

  getCardsDetails(id: number){
    return this.http.get<Cards[]>(`${environment.rooturl}${AppConstants.CARDS_API_URL}?id=${id}`, { observe: 'response', withCredentials: true });
  }

  getNoticeDetails(){
    return this.http.get(`${environment.rooturl}${AppConstants.NOTICES_API_URL}`, { observe: 'response' });
  }

  saveMessage(contact : Contact){
    return this.http.post(`${environment.rooturl}${AppConstants.CONTACT_API_URL}`, contact, { observe: 'response'});
  }

}