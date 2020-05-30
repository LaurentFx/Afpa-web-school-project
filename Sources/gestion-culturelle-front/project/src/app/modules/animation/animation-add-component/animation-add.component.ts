import { Component, OnInit } from '@angular/core';
import { AnimationService } from '../../../service/animation.service';
import { Router } from '@angular/router';
import { AnimationDto } from '../../../model/animationDto';
import { ToastrService } from 'ngx-toastr';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../../../security/auth.service';
import { User } from '../../../model/user';

@Component({
  selector: 'app-animation-add',
  templateUrl: './animation-add.component.html',
  styleUrls: ['./animation-add.component.css']
})
export class AnimationAddComponent implements OnInit {
 
  animation: AnimationDto;
  userCourant: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;

  constructor(private animationService: AnimationService,
     private router: Router, private authService: AuthService,
     private toastrService: ToastrService) { }

  ngOnInit() {
    this.animation = new AnimationDto();
    this.userCourant = this.authService.getCurrentUser();
    this.animation.animateur=this.userCourant;
  }

  add(): void {
    let nom = this.animation.label;

    this.animationService.add(this.animation).subscribe(
      res => {
         if (res) {
          this.toastrService.error('L animation '+nom +' existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Nouvelle animation : ' +nom, 'Ajout Ok')
        }  
        this.animationService.subjectMiseAJour.next(0);   
        this.goHome();
      }
    );
    this.animation = new AnimationDto();
  }

  goHome() {
    this.router.navigate(['/public/animation-list']);

  }

}
