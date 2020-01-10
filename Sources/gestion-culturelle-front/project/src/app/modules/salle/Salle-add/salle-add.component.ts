import { Component, OnInit } from '@angular/core';
import { SalleService } from 'src/app/service/Salle.service';
import { Router } from '@angular/router';
import { SalleModel } from 'src/app/model/Salle';
import { TypeDeSalleModel } from '../../../model/type-de-salle';
import { TypeSalleService } from '../../../service/typeSalle.service';

@Component({
  selector: 'app-salle-add',
  templateUrl: './salle-add.component.html',
  styleUrls: ['./salle-add.component.css']
})
export class SalleAddComponent implements OnInit {

  salle: SalleModel;
  typeSalles: TypeDeSalleModel[];

  constructor(private typeSalleService: TypeSalleService,private salleService: SalleService, private router: Router) { }

  ngOnInit() {
    this.typeSalles = [];
    this.salle = new SalleModel();
    this.salle.typeSalle=new TypeDeSalleModel();
   
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

  add(): void {
    this.salleService.add(this.salle).subscribe(
      res => {       
        console.log("Ajout Ok Laurent");
        this.goHome();
      },error =>console.log(error)
      
    );
    this.salle = new SalleModel();
  }

  goHome() {
    this.router.navigate(['/salle-list']);

  }
}
