import { Component, OnInit, Input } from '@angular/core';
import { TypeSalleModel } from '../../../model/typeSalle';
import { TypeSalleService } from '../../../service/typeSalle.service';
import { Router } from '@angular/router';

@Component({
  selector: 'cda-typesalle-detail',
  templateUrl: './typesalle-detail.component.html',
  styleUrls: ['./typesalle-detail.component.css']
})
export class TypeSalleDetailComponent implements OnInit {

  @Input() typesalle: TypeSalleModel;

  constructor(private typeSalleService: TypeSalleService, private router: Router) { }

  ngOnInit() {
  
  }

  delete(id:number) {
    this.typeSalleService.delete(id).subscribe(
      res=>{
        this.typeSalleService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(){
    this.router.navigateByUrl('/typesalle-update/'+this.typesalle.id)
  }
   

  redirectToShow() {
    this.router.navigateByUrl('/typesalle-show/'+this.typesalle.id)
  }


}



