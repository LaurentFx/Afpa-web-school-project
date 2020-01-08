import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.css']
})
export class AppHeaderComponent implements OnInit {

  isConnected: boolean;

  constructor(private router : Router, private authService: AuthService) { }

  ngOnInit() {
    this.isConnected = this.authService.isConnected;
    this.authService.subjectConnexion.subscribe(
      res=>{
        this.isConnected = this.authService.isConnected;
      }
    );
  }

  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/login');
    this.isConnected = false;
  }

}
