import { Component, OnInit } from '@angular/core';
import { AnimationService } from '../../../service/animation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AnimationDto } from '../../../model/animationDto';
import { faHome, faCheckSquare } from '@fortawesome/free-solid-svg-icons';
import { User } from '../../../model/user';


@Component({
  selector: 'app-animation-show',
  templateUrl: './animation-show.component.html',
  styleUrls: ['./animation-show.component.css']
})
export class AnimationShowComponent implements OnInit {

  animation: AnimationDto;
  faHome = faHome;

  constructor(private route: ActivatedRoute, private animationService: AnimationService, private router: Router) { }
  
  ngOnInit() {
    this.animation = new AnimationDto();
    this.animation.animateur = new User();

    let id = this.route.snapshot.params['id'];

    this.animationService.getOne(id).subscribe(
      res => {
        this.animation = res;
      }
    );
  }

}

