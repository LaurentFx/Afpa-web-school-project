import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-navebar',
  templateUrl: './navebar.component.html',
  styleUrls: ['./navebar.component.css']
})
export class NavebarComponent implements OnInit {

  isConnected: boolean;
  isAdmin: boolean;
  user: String;
  role: String;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.isConnected = this.authService.isConnected();
    this.user = this.authService.getCurrentUser().nom;
    
    /* Erreur
    if(this.authService.getCurrentUser()){
      this.isAdmin = this.authService.getCurrentUser().role === 'ADMIN';
    }
      */
    /* Test pour afficher le user 
    this.authService.subjectMiseAJour.subscribe(
      res => {
        this.user = this.authService.getCurrentUser().nom;
       

      }
    );*/


    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

        /* Erreur
        if(this.authService.getCurrentUser()){
           this.isAdmin = this.authService.getCurrentUser().role === 'ADMIN';
         }*/
      }
    );
  }


  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/login');
    this.isConnected = false;
  }

}
