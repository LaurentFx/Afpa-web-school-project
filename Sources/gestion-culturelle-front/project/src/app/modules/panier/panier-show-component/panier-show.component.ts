import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';

@Component({
  selector: 'app-panier-show',
  templateUrl: './panier-show.component.html',
  styleUrls: ['./panier-show.component.css']
})
export class PanierShowComponent implements OnInit {

  panier: PanierDto;
  manifestations: ManifestationDto[];

  constructor(private route: ActivatedRoute,
     private panierService: PanierService,
     private manifestationService : ManifestationService,
      private router: Router) { }
  
  ngOnInit() {
    this.panier = new PanierDto();
    //this.panier.manifestation = new ManifestationDto ();

    let id = this.route.snapshot.params['id'];

    this.panierService.getOne(id).subscribe(
      res => {
        this.panier = res;
        console.log(res);
      }
    );

    this.manifestationService.subjectMiseAJour.subscribe(
      res=> {
        this.manifestationService.getAll().subscribe(
          donnees =>{
			  this.manifestations = donnees; 
          }
        );
      }
    );

    this.manifestationService.getAll().subscribe(
      resultat =>{
          this.manifestations = resultat; 
        
      }
    );

  }

}

