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
  user: String;
  role: RoleDto;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
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
