import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';

@Component({
  selector: 'app-panier-add',
  templateUrl: './panier-add.component.html',
  styleUrls: ['./panier-add.component.css']
})
export class PanierAddComponent implements OnInit {

  panier: PanierDto;
  manifestations: ManifestationDto[];

  constructor(private manifestationService: ManifestationService,
     private panierService: PanierService,
      private router: Router) { }

  ngOnInit() {
    this.panier = new PanierDto();
    this.manifestations = [];
    this.panier.manifestation = new ManifestationDto();

    this.manifestationService.subjectMiseAJour.subscribe(
      res => {
        this.manifestationService.getAll().subscribe(
          donnees => {
            this.manifestations = donnees;
          }
        );
      }
    );

    this.manifestationService.getAll().subscribe(
      resultat => {
        this.manifestations = resultat;
      }
    );


  }

  add(): void {
    this.panierService.add(this.panier).subscribe(
      res => {
        this.panierService.subjectMiseAJour.next(0);
        console.log("Ajout Ok ");
        this.goHome();
      }
    );
    this.panier = new PanierDto();
    this.panier.manifestation = new ManifestationDto();
  }

  goHome() {
    this.router.navigate(['/panier-list']);

  }

}
