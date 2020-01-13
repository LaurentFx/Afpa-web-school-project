import { Component, OnInit } from '@angular/core';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router } from '@angular/router';
import { TypeSalleDto } from '../../../model/typeSalleDto';

@Component({
  selector: 'app-typesalle-add',
  templateUrl: './typesalle-add.component.html',
  styleUrls: ['./typesalle-add.component.css']
})
export class TypeSalleAddComponent implements OnInit {

  typeSalle: TypeSalleDto;

  constructor(private typeSalleService: TypeSalleService, private router: Router) { }

  ngOnInit() {
    this.typeSalle = new TypeSalleDto();
  }

  add(): void {
    this.typeSalleService.add(this.typeSalle).subscribe(
      res => {
        this.typeSalleService.subjectMiseAJour.next(0);
        console.log("Ajout Ok ");
        this.goHome();
      }
    );
    this.typeSalle = new TypeSalleDto();
  }

  goHome() {
    this.router.navigate(['/typesalle-list']);

  }

}
