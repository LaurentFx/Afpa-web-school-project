import { Component, OnInit } from '@angular/core';
import { SalleModel } from '../../../model/salle';
import { ActivatedRoute, Router } from '@angular/router';
import { SalleService } from '../../../service/Salle.service';

@Component({
  selector: 'app-salle-show',
  templateUrl: './salle-show.component.html',
  styleUrls: ['./salle-show.component.css']
})
export class SalleShowComponent implements OnInit {

  salle: SalleModel;

  constructor(private route: ActivatedRoute, private salleService:SalleService, private router: Router) { }

  ngOnInit() {
    this.salle = new SalleModel();

    let id = this.route.snapshot.params['id'];

    this.salleService.getOne(id).subscribe(
      res => {
        this.salle = res;
        console.log(res);
      }
    );

  }


}
