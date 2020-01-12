import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SalleModel } from '../../../model/salle';
import { SalleService } from '../../../service/Salle.service';
import { TypeDeSalleModel } from '../../../model/type-de-salle';
import { TypeSalleService } from '../../../service/typeSalle.service';

@Component({
  selector: 'app-salle-update',
  templateUrl: './salle-update.component.html',
  styleUrls: ['./salle-update.component.css']
})
export class SalleUpdateComponent implements OnInit {

  id: number;
  salle: SalleModel;
  typeSalles: TypeDeSalleModel[];

  constructor(private route: ActivatedRoute, private typeSalleService: TypeSalleService,private salleService: SalleService, private router: Router) { }

  ngOnInit() {
    this.typeSalles = [];
    this.salle = new SalleModel();
    this.salle.typeSalle=new TypeDeSalleModel();

    let id = this.route.snapshot.params['id'];

    this.salleService.getOne(id).subscribe(
      res => {
        this.salle = res;
      }
    );

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

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.salleService.update(id, this.salle).subscribe(
      res => {
       console.log("Modification Ok");
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/salle-list']);
  }

}
