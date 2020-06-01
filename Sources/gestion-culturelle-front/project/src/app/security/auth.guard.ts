import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> |
     Promise<boolean | UrlTree> | boolean | UrlTree {
    if (! (localStorage.getItem('isConnected') &&
      localStorage.getItem('access_token') &&
      localStorage.getItem('current_user') )
    ) {
      localStorage.removeItem('isConnected');
      localStorage.removeItem('access_token');
      localStorage.removeItem('current_user');
      this.router.navigateByUrl('/public/login');
      return false;
    } else if (Boolean(localStorage.getItem('isConnected'))) {
      console.log("TEST Local storage current user"+localStorage.getItem('current_user'));
      return true;
    } else {
      this.router.navigateByUrl('/public/login');
      return false;
    }
  }

}
