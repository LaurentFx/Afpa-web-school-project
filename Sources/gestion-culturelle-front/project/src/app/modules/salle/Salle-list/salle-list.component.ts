import { Component, OnInit } from '@angular/core';
import { SalleDto } from '../../../model/salleDto';
import { Router } from '@angular/router';
import { SalleService } from '../../../service/salle.service';

@Component({
  selector: 'app-salle-list',
  templateUrl: './salle-list.component.html',
  styleUrls: ['./salle-list.component.css']
})
export class SalleListComponent implements OnInit {

  salles: SalleDto[];
  
  constructor(private salleService: SalleService,private router: Router) { }

  ngOnInit() {

    this.salleService.subjectMiseAJour.subscribe(
      res=> {
        this.salleService.getAll().subscribe(
          donnees =>{
			  this.salles = donnees; 
          }
        );
      }
    );

    this.salleService.getAll().subscribe(
      resultat =>{
          this.salles = resultat; 
        
      }
    );
  }

 delete(id:number) {
    this.salleService.delete(id).subscribe(
      res=>{
        this.salleService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/salle-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/salle-show/'+id)
  }
}
