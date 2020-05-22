import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../../model/user';
import { InscriptionService } from '../../../service/inscription.service';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  user: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;


  constructor(private router: Router, private inscriptionService: InscriptionService,
    private route: ActivatedRoute, private authService: AuthService,private toastrService: ToastrService) { }

  ngOnInit() {
    this.user = new User();
   
  }

  add(): void {
    this.inscriptionService.add(this.user).subscribe(
      res => {
        this.toastrService.success('Bienvenue ' +this.user.nom, 'Inscription réussie !')
        this.goHome();
      }
    );
  }


  goHome() {
    this.router.navigate(['/public']);
  }


}
