import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';

@Component({
  selector: 'app-manifestation-list',
  templateUrl: './manifestation-list.component.html',
  styleUrls: ['./manifestation-list.component.css']
})
export class ManifestationListComponent implements OnInit {

  manifestations: ManifestationDto[];
  
  constructor(private manifestationService: ManifestationService,private router: Router) { }

  ngOnInit() {

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
    this.manifestationService.delete(id).subscribe(
      res=>{
        this.manifestationService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/manifestation-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/manifestation-show/'+id)
  }
}
