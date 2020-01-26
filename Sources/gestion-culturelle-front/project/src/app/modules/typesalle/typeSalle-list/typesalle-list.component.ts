import { Component, OnInit } from '../../../../../node_modules/@angular/core';
import { TypeSalleDto } from '../../../model/typeSalleDto';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router } from '../../../../../node_modules/@angular/router';
import { AuthService } from '../../../service/auth.service';

@Component({
  selector: 'app-typesalle-list',
  templateUrl: './typesalle-list.component.html',
  styleUrls: ['./typesalle-list.component.css']
})
export class TypeSalleListComponent implements OnInit {

  typeSalles: TypeSalleDto[];
  
  
  constructor(private typeSalleService: TypeSalleService,private router: Router) { }

  ngOnInit() {
    this.typeSalleService.subjectMiseAJour.subscribe(
      res=> {
        this.typeSalleService.getAll().subscribe(
          donnees =>{
			  this.typeSalles = donnees; 
          }
        );
      }
    );

    this.typeSalleService.getAll().subscribe(
      resultat =>{
          this.typeSalles = resultat; 
               }
    );
  }

  delete(id:number) {
    this.typeSalleService.delete(id).subscribe(
      res=>{
        this.typeSalleService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/typesalle-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/typesalle-show/'+id)
  }
}
