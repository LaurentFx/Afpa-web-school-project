import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { RoleDto } from '../model/roleDto';


@Component({
  selector: 'app-navebar',
  templateUrl: './navebar.component.html',
  styleUrls: ['./navebar.component.css']
})
export class NavebarComponent implements OnInit {

  isConnected: boolean;
  isResp: boolean;
  isClient: boolean;
  isAnim: boolean;
  isAdmin: boolean;
  isRespAdmin: boolean;
  user: String;
  role: RoleDto;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
      this.isClient = this.authService.getCurrentUser().role.label === 'CLIENT';
      this.isAnim = this.authService.getCurrentUser().role.label === 'ANIM';
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';

      this.user = this.authService.getCurrentUser().nom;
      this.role = this.authService.getCurrentUser().role;
    }

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

        if (res == 0) {
          this.isResp = false;
          this.user = '';
          this.role = null;
        } else {
          const userCourant = this.authService.getCurrentUser();
          this.isResp = userCourant.role.label === 'RESP';
          this.isClient = userCourant.role.label === 'CLIENT';
          this.isAnim = userCourant.role.label === 'ANIM';
          this.isAdmin = userCourant.role.label === 'ADMIN';
          this.isRespAdmin = userCourant.role.label === ('RESP' || 'ADMIN');
          this.user = userCourant.nom;
          this.role = userCourant.role;
        }
      }
    );
  }

  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/public/login');
    this.isConnected = false;
  }

  redirectToProfil(): void {
    this.router.navigateByUrl('/public/profil')

  }

}
