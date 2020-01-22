import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-panier-add',
  templateUrl: './panier-add.component.html',
  styleUrls: ['./panier-add.component.css']
})
export class PanierAddComponent implements OnInit {

  date: string;
  panier: PanierDto;
  manifestation: ManifestationDto;
  manifestations: ManifestationDto[];
  user: User;

  constructor(private manifestationService: ManifestationService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService,
  ) { }

  ngOnInit() {
    this.panier = new PanierDto();
    this.panier.manifestation = new ManifestationDto();
    this.manifestations = [];

    let idUser = this.authService.getCurrentUser().id;

    this.panierService.getUser(idUser).subscribe(
      res => {
        this.panier = res;
      }
    );

    let id = this.route.snapshot.params['id'];
    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }
    );

    let nbreBillets = this.panier.nbreBillets;

  }

  add(): void {
    this.panierService.add(this.panier).subscribe(
      res => {
        this.panierService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
    this.panier = new PanierDto();
    this.panier.manifestation = new ManifestationDto();
  }


  addPanier(): void {
    let nbreBillets = this.panier.nbreBillets;
    this.panierService.addPanier(this.manifestation).subscribe(
      res => {
        this.panierService.subjectMiseAJour.next(0);
        // this.panier = res;

      }
    )


  }


  goHome() {

    this.router.navigate(['/panier-list']);

  }

}
