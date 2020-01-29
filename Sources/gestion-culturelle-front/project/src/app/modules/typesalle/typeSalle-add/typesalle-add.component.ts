import { Component, OnInit } from '@angular/core';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router } from '@angular/router';
import { TypeSalleDto } from '../../../model/typeSalleDto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-typesalle-add',
  templateUrl: './typesalle-add.component.html',
  styleUrls: ['./typesalle-add.component.css']
})
export class TypeSalleAddComponent implements OnInit {

  typeSalle: TypeSalleDto;

  constructor(private typeSalleService: TypeSalleService,
    private router: Router, private toastrService: ToastrService) { }

  ngOnInit() {
    this.typeSalle = new TypeSalleDto();
  }

  add(): void {
    //this.typeSalle = new TypeSalleDto();
    this.typeSalleService.add(this.typeSalle).subscribe(
      
      res => {
        this.typeSalleService.subjectMiseAJour.next(0);
        if (res) {
          this.toastrService.error(this.typeSalle.label + ' existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Nouveau type de salle : ' + this.typeSalle.label, 'Ajout Ok')
        }

        this.goHome();


      }
    );
    this.typeSalle = new TypeSalleDto();
  }

  goHome() {
    this.router.navigate(['/typesalle-list']);
  }

}
