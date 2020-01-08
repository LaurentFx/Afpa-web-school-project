import { Component, OnInit } from '@angular/core';
import { SalleModel } from '../../../model/salle';
import { SalleService } from '../../../service/salle.service';

@Component({
  selector: 'app-salle-list',
  templateUrl: './salle-list.component.html',
  styleUrls: ['./salle-list.component.css']
})
export class SalleListComponent implements OnInit {

  salles: SalleModel[];
  
  constructor(private roleService: SalleService) { }

  ngOnInit() {

    this.roleService.subjectMiseAJour.subscribe(
      res=> {
        this.roleService.getAll().subscribe(
          donnees =>{
			  this.salles = donnees; 
          }
        );
      }
    );

    this.roleService.getAll().subscribe(
      resultat =>{
          this.salles = resultat; 
          console.log('c est moi',this.salles);
      }
    );
  }

}
