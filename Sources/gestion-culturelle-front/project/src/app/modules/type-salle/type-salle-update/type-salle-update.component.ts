import { Component, OnInit } from '@angular/core';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TypeSalleModel } from 'src/app/model/typeSalle';


@Component({
  selector: 'app-type-salle-update',
  templateUrl: './type-salle-update.component.html',
  styleUrls: ['./type-salle-update.component.css']
})
export class TypeSalleUpdateComponent implements OnInit {

  id: number;
  typeSalle: TypeSalleModel;

  constructor(private route: ActivatedRoute, private typeSalleService: TypeSalleService, private router: Router) { }

  ngOnInit() {
    this.typeSalle = new TypeSalleModel();

    let id = this.route.snapshot.params['id'];

    this.typeSalleService.getOne(id).subscribe(
      res => {
        this.typeSalle = res;
      }
    );
  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.typeSalleService.update(id, this.typeSalle).subscribe(
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
    this.router.navigate(['/typesalle-list']);
  }

}

