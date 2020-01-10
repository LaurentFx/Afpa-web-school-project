import { Component, OnInit } from '@angular/core';
import { SalleService } from 'src/app/service/Salle.service';
import { Router } from '@angular/router';
import { SalleModel } from 'src/app/model/Salle';

@Component({
  selector: 'app-salle-add',
  templateUrl: './salle-add.component.html',
  styleUrls: ['./salle-add.component.css']
})
export class SalleAddComponent implements OnInit {

  salle: SalleModel;

  constructor(private salleService: SalleService, private router: Router) { }

  ngOnInit() {
    this.salle = new SalleModel();
  }

  add(): void {
    this.salleService.add(this.salle).subscribe(
      res => {       
        console.log("Ajout Ok ");
        this.goHome();
      },error =>console.log(error)
      
    );
    this.salle = new SalleModel();
  }

  goHome() {
    this.router.navigate(['/salle-list']);

  }
}
