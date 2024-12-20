import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { User } from 'src/app/model';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let httpHeaders = new HttpHeaders();
    let user: User | undefined = undefined;
    const userdetails = sessionStorage.getItem('userdetails');
    if(userdetails){
      user = JSON.parse(userdetails);
    }
    if(user && user.password && user.email){
      httpHeaders = httpHeaders.append('Authorization', `Basic ${window.btoa(`${user.email}:${user.password}`)}`);
    } else {
      const authorization = window.sessionStorage.getItem('Authorization');
      if(authorization) {
        httpHeaders = httpHeaders.append('Authorization', `Bearer ${authorization}`);
      }
    }
    const xsrf = sessionStorage.getItem('XSRF-TOKEN');
    if(xsrf) {
      httpHeaders = httpHeaders.append('X-XSRF-TOKEN', xsrf);
    }

    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
    const xhr = req.clone({
      headers: httpHeaders
    });
    return next.handle(xhr).pipe(tap(
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return;
          }
          this.router.navigate(['dashboard']);
        }
      }));
  }
}
