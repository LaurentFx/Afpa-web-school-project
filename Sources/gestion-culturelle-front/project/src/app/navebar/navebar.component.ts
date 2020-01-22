import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { RoleDto } from '../model/roleDto';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-navebar',
  templateUrl: './navebar.component.html',
  styleUrls: ['./navebar.component.css']
})
export class NavebarComponent implements OnInit {

  isConnected: boolean;
  isAdmin: boolean;
  user: String;
  role: RoleDto;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {

    this.isConnected = this.authService.isConnected();
    this.user = this.authService.getCurrentUser().nom;
    this.role = this.authService.getCurrentUser().role;

    if (this.authService.getCurrentUser()) {
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
    }

    /*  Test pour afficher le user */
    this.authService.subjectMiseAJour.subscribe(
      res => {
        this.authService.subjectMiseAJour.next(1);
        this.user = this.authService.getCurrentUser().nom;
        this.role = this.authService.getCurrentUser().role;

      }
    );

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

        if (this.authService.getCurrentUser()) {
          this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
        }
      }
    );
  }


  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/login');
    this.isConnected = false;
  }

}
