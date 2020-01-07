import { Component, OnInit } from '@angular/core';
import { TypeSalleModel } from '../model/TypeSalle';
import { TypeSalleService } from '../service/TypeSalle.service';

@Component({
  selector: 'app-typesalle-list',
  templateUrl: './typesalle-list.component.html',
  styleUrls: ['./typesalle-list.component.css']
})
export class TypeSalleListComponent implements OnInit {

  typeSalles: TypeSalleModel[];
  
  constructor(private typeSalleService: TypeSalleService) { }

  ngOnInit() {

    this.typeSalleService.subjectMiseAJour.subscribe(
      res=> {
        this.typeSalleService.getAll().subscribe(
          donnees =>{
			  this.typeSalles = donnees; 
          }
        );
      }
    );

    this.typeSalleService.getAll().subscribe(
      resultat =>{
          this.typeSalles = resultat; 
          console.log('c est moi',this.typeSalles);
      }
    );
  }

}
