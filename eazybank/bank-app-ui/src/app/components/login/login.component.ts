import { Component, OnInit } from '@angular/core';
import { NgForm, FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model';
import { LoginService } from 'src/app/services';
import { getCookie } from 'typescript-cookie';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    imports: [FormsModule, NgIf]
})
export class LoginComponent implements OnInit {
  authStatus: string = '';
  model = new User();

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {}

  validateUser(loginForm: NgForm) {
    this.loginService.validateLoginDetails(this.model).subscribe(
      responseData => {
        window.sessionStorage.setItem('Authorization', responseData.headers.get('Authorization')!);
        this.model = responseData.body!;
        this.model.authStatus = 'AUTH';
        window.sessionStorage.setItem('userdetails',JSON.stringify(this.model));
        const xsrf = getCookie('XSRF-TOKEN')!;
        window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        this.router.navigate(['dashboard']);
      });

  }

}
