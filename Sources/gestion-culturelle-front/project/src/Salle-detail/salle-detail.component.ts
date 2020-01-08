import { Component, OnInit, Input } from '@angular/core';
import { SalleModel } from '../model/Salle';

@Component({
  selector: 'cda-salle-detail',
  templateUrl: './salle-detail.component.html',
  styleUrls: ['./salle-detail.component.css']
})
export class SalleDetailComponent implements OnInit {

  @Input() salle: SalleModel;

  constructor() { }

  ngOnInit() {
   console.log('couc oucou cou ou ');
  }

}
