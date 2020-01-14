import { Component, OnInit } from '@angular/core';
import { AnimationService } from '../../../service/animation.service';
import { Router } from '@angular/router';
import { AnimationDto } from '../../../model/animationDto';

@Component({
  selector: 'app-animation-list',
  templateUrl: './animation-list.component.html',
  styleUrls: ['./animation-list.component.css']
})
export class AnimationListComponent implements OnInit {

  animations: AnimationDto[];
  
  constructor(private animationService: AnimationService,private router: Router) { }

  ngOnInit() {

    this.animationService.subjectMiseAJour.subscribe(
      res=> {
        this.animationService.getAll().subscribe(
          donnees =>{
			  this.animations = donnees; 
          }
        );
      }
    );

    this.animationService.getAll().subscribe(
      resultat =>{
          this.animations = resultat; 
               }
    );
  }

  delete(id:number) {
    this.animationService.delete(id).subscribe(
      res=>{
        this.animationService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/animation-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/animation-show/'+id)
  }
}
