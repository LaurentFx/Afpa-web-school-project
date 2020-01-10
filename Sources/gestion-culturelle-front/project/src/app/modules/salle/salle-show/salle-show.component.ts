import { Component, OnInit } from '@angular/core';
import { TypeDeSalleModel } from 'src/app/model/type-de-salle';

@Component({
  selector: 'app-salle-show',
  templateUrl: './salle-show.component.html',
  styleUrls: ['./salle-show.component.css']
})
export class SalleShowComponent implements OnInit {
  salle: TypeDeSalleModel;

  constructor() { }

  ngOnInit() {
  }

}
