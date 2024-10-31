import { Injectable,inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot,Router } from '@angular/router';
import { User } from '../model/user.model';

@Injectable()
export class AuthActivateRouteGuard {
  constructor(private router: Router){}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
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

export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(AuthActivateRouteGuard).canActivate(next, state);
}
