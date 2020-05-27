import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../../model/user';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  user: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;


  constructor(private router: Router, private userService: UserService,
    private route: ActivatedRoute, private authService: AuthService,
    private toastrService: ToastrService) { }

  ngOnInit() {
    this.user = new User();
   
  }

  add(): void {
    this.userService.addClient(this.user).subscribe(
      res => {
        if (res) {
          this.toastrService.error('Le user '+this.user.nom +' existe déjà', 'Inscription impossible')
        } else {
          this.toastrService.success('Bienvenue ' +this.user.nom, 'Inscription réussie !')
        }
        this.userService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
  }


  goHome() {
    this.router.navigate(['/public']);
  }


}
