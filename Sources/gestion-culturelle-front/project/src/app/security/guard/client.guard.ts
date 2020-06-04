import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { User } from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class ClientGuard implements CanActivate {

  user: User;
  constructor(private router: Router, private authService: AuthService) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> |
    Promise<boolean | UrlTree> | boolean | UrlTree {
      this.user = this.authService.getCurrentUser();
    if (!(localStorage.getItem('isConnected') &&
      localStorage.getItem('access_token') &&
      localStorage.getItem('current_user'))
    ) {
      localStorage.removeItem('isConnected');
      localStorage.removeItem('access_token');
      localStorage.removeItem('current_user');
      this.router.navigateByUrl('/public/login');
      this.authService.logout();
      return false;
    } else if (Boolean(localStorage.getItem('isConnected'))&&this.user.role.label==='CLIENT') {
     
      console.log("ROLE CLIENT");

      return true;
    } else {
      this.router.navigateByUrl('/public/login');
      this.authService.logout();
      return false;
    }
  }

}
