import { Injectable,inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { User } from 'src/app/model';

@Injectable()
export class AuthActivateRouteGuard {
  constructor(private router: Router){}

  canActivate() {
    let user = new User();
    if(sessionStorage.getItem('userdetails')){
      user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    if(user.email.length === 0){
      this.router.navigate(['login']);
    }
    return user.email.length !== 0;
  }

}

export const AuthGuard: CanActivateFn = (): boolean => {
  return inject(AuthActivateRouteGuard).canActivate();
}
