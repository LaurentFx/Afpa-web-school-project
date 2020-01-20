import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';

@Component({
  selector: 'app-panier-list',
  templateUrl: './panier-list.component.html',
  styleUrls: ['./panier-list.component.css']
})
export class PanierListComponent implements OnInit {

  paniers: PanierDto[];
  manifestations: ManifestationDto[];
  
  constructor(private panierService: PanierService,
     private manifestationService : ManifestationService,
      private router: Router) { }

  ngOnInit() {

    this.panierService.subjectMiseAJour.subscribe(
      res=> {
        this.panierService.getAll().subscribe(
          donnees =>{
			  this.paniers = donnees; 
          }
        );
      }
    );

    this.panierService.getAll().subscribe(
      resultat =>{
          this.paniers = resultat; 
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

  

  delete(id:number) {
    this.panierService.delete(id).subscribe(
      res=>{
        this.panierService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/panier-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/panier-show/'+id)
  }
}
