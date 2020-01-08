import { Component, OnInit } from '@angular/core';
import { SalleService } from '../../../service/Salle.service';

@Component({
  selector: 'app-salle-add',
  templateUrl: './salle-add.component.html',
  styleUrls: ['./salle-add.component.css']
})
export class SalleAddComponent implements OnInit {

 salle: string;

  constructor(private salleService: SalleService) { }

  ngOnInit() {
    this.salle='';
  }

  add() : void {
    this.salleService.add(this.salle).subscribe(
      res=>{
        this.salleService.subjectMiseAJour.next(0);
        console.log("ajout salle avec succes! ");
      }
    );
    this.salle='';
  }

}
