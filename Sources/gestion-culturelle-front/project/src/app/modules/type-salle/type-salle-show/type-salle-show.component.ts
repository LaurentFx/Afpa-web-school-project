import { Component, OnInit } from '@angular/core';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TypeSalleModel } from 'src/app/model/typeSalle';


@Component({
  selector: 'app-type-salle-show',
  templateUrl: './type-salle-show.component.html',
  styleUrls: ['./type-salle-show.component.css']
})
export class TypeSalleShowComponent implements OnInit {

  typeSalle: TypeSalleModel;

  constructor(private route: ActivatedRoute, private typeSalleService: TypeSalleService, private router: Router) { }
  
  ngOnInit() {
    this.typeSalle = new TypeSalleModel();

    let id = this.route.snapshot.params['id'];

    this.typeSalleService.getOne(id).subscribe(
      res => {
        this.typeSalle = res;
        console.log(res);
      }
    );
  }

}

