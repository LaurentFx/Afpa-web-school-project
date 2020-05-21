import { Component, OnInit } from '@angular/core';
import { AnimationService } from '../../../service/animation.service';
import { Router } from '@angular/router';
import { AnimationDto } from '../../../model/animationDto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-animation-add',
  templateUrl: './animation-add.component.html',
  styleUrls: ['./animation-add.component.css']
})
export class AnimationAddComponent implements OnInit {
 
  animation: AnimationDto;

  constructor(private animationService: AnimationService,
     private router: Router,
     private toastrService: ToastrService) { }

  ngOnInit() {
    this.animation = new AnimationDto();
    
  }

  add(): void {
    let nom = this.animation.label;

    this.animationService.add(this.animation).subscribe(
      res => {

        this.animationService.subjectMiseAJour.next(0);
         if (res) {
          this.toastrService.error('L animation '+nom +' existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Nouvelle animation : ' +nom, 'Ajout Ok')
        }     
        this.goHome();
      }
    );
    this.animation = new AnimationDto();
  }

  goHome() {
    this.router.navigate(['/public/animation-list']);

  }

}
