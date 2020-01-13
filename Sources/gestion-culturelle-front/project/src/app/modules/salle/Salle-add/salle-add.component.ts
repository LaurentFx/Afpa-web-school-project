import { Component, OnInit } from '@angular/core';
import { SalleService } from 'src/app/service/Salle.service';
import { Router } from '@angular/router';
import { SalleDto } from '../../../model/salleDto';
import { TypeSalleDto } from '../../../model/typeSalleDto';
import { TypeSalleService } from '../../../service/typeSalle.service';

@Component({
  selector: 'app-salle-add',
  templateUrl: './salle-add.component.html',
  styleUrls: ['./salle-add.component.css']
})
export class SalleAddComponent implements OnInit {

  salle: SalleDto;
  typeSalles: TypeSalleDto[];

  constructor(private typeSalleService: TypeSalleService, private salleService: SalleService, private router: Router) { }

  ngOnInit() {
    this.typeSalles = [];
    this.salle = new SalleDto();
    this.salle.typeSalle = new TypeSalleDto();

    this.typeSalleService.subjectMiseAJour.subscribe(
      res => {
        this.typeSalleService.getAll().subscribe(
          donnees => {
            this.typeSalles = donnees;
          }
        );
      }
    );

    this.typeSalleService.getAll().subscribe(
      resultat => {
        this.typeSalles = resultat;
      }
    );

  }

  add(): void {
    this.salleService.add(this.salle).subscribe(
      res => {
        console.log("Ajout Ok");
        this.goHome();
      }

    );
    this.salle = new SalleDto();
  }

  goHome() {
    this.router.navigate(['/salle-list']);

  }
}
