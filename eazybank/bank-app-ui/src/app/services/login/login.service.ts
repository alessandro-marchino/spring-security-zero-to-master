import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { User } from 'src/app/model';
import { AppConstants } from 'src/app/constants';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  validateLoginDetails(user: User): Observable<HttpResponse<User>> {
    window.sessionStorage.setItem('userdetails', JSON.stringify(user));
    return this.http.get<User>(environment.rooturl + AppConstants.LOGIN_API_URL, { observe: 'response', withCredentials: true });
  }

}
