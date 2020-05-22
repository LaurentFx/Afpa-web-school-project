import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AnimationDto } from '../../../model/animationDto';
import { SalleDto } from '../../../model/salleDto';
import { User } from '../../../model/user';
import { faHome } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-manifestation-show',
  templateUrl: './manifestation-show.component.html',
  styleUrls: ['./manifestation-show.component.css']
})
export class ManifestationShowComponent implements OnInit {

  manifestation: ManifestationDto;
  faHome = faHome;

  constructor(private route: ActivatedRoute, private manifestationService:ManifestationService, private router: Router) { }

  ngOnInit() {
    this.manifestation = new ManifestationDto();
    this.manifestation.salle = new SalleDto();
    this.manifestation.validateur = new User();
    this.manifestation.animation = new AnimationDto();

    let id = this.route.snapshot.params['id'];

    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }
    );

  }


}

