import { Component, OnInit, Input } from '@angular/core';
import { TypeSalleModel } from '../model/TypeSalle';

@Component({
  selector: 'app-typesalle-detail',
  templateUrl: './typesalle-detail.component.html',
  styleUrls: ['./typesalle-detail.component.css']
})
export class TypeSalleDetailComponent implements OnInit {

  @Input() typesalle: TypeSalleModel;

  constructor() { }

  ngOnInit() {
   
  }

}
