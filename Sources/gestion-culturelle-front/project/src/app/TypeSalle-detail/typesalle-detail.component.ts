import { Component, OnInit, Input } from '@angular/core';
import { TypeSalleModel } from '../model/typeSalle';

@Component({
  selector: 'cda-typesalle-detail',
  templateUrl: './typesalle-detail.component.html',
  styleUrls: ['./typesalle-detail.component.css']
})
export class TypeSalleDetailComponent implements OnInit {

  @Input() typesalle: TypeSalleModel;

  constructor() { }

  ngOnInit() {
   console.log('couc oucou cou ou ');
  }

}
