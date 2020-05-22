import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { AuthService } from '../../../service/auth.service';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  id: number;
  user: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;


  constructor(private router: Router, private userService: UserService,
    private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    this.user = new User();

    /* this.user = this.authService.getCurrentUser().nom; */
    let id = this.authService.getCurrentUser().id;

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    )

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
