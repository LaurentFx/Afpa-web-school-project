import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { AuthService } from '../../../service/auth.service';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { faHome, faCheckSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-profil-update-component',
  templateUrl: './profil-update.component.html',
  styleUrls: ['./profil-update.component.css']
})
export class ProfilUpdateComponent implements OnInit {
 
  isClient: boolean;
  id: number;
  user: User;
  faHome = faHome;
  faCheckSquare = faCheckSquare;

  constructor(private router: Router, private userService: UserService,
    private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    this.user = new User();

    let id = this.authService.getCurrentUser().id;

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    )

    if (this.authService.getCurrentUser()) {
      const userCourant = this.authService.getCurrentUser();
      this.isClient = userCourant.role.label === 'CLIENT';
      }

    }


  update(): void {
    let id = this.authService.getCurrentUser().id;
    this.userService.update(id, this.user).subscribe(
      res => {
        this.goHome();
      }
    );
  }
  onSubmit() {
    this.update();
  }
  goHome() {
    this.router.navigate(['/public']);
  }



}
