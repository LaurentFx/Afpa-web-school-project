import { Component, OnInit } from '@angular/core';
import { TypeSalleService } from '../service/typeSalle.service';

@Component({
  selector: 'app-typesalle-add',
  templateUrl: './typesalle-add.component.html',
  styleUrls: ['./typesalle-add.component.css']
})
export class TypeSalleAddComponent implements OnInit {

  typeSalle: string;

  constructor(private typeSalleService: TypeSalleService) { }

  ngOnInit() {
    this.typeSalle='';
  }

  add() : void {
    this.typeSalleService.add(this.typeSalle).subscribe(
      res=>{
        this.typeSalleService.subjectMiseAJour.next(0);
        console.log("ajout avec succes! ");
      }
    );
    this.typeSalle='';
  }

}
