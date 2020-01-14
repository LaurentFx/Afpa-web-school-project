import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AnimationDto } from '../../../model/animationDto';
import { SalleDto } from '../../../model/salleDto';
import { SalleService } from '../../../service/Salle.service';
import { AnimationService } from '../../../service/animation.service';

@Component({
  selector: 'app-manifestation-update',
  templateUrl: './manifestation-update.component.html',
  styleUrls: ['./manifestation-update.component.css']
})
export class ManifestationUpdateComponent implements OnInit {

  id: number;
  manifestation: ManifestationDto;
  animations: AnimationDto[];
  salles: SalleDto[];

  constructor(private salleService : SalleService, private animationService : AnimationService,private route: ActivatedRoute, private manifestationService: ManifestationService, private router: Router) { }

  ngOnInit() {
    this.manifestation = new ManifestationDto();
    this.salles =[];
    this.animations = [];
    this.manifestation.salle = new SalleDto();
    this.manifestation.animation = new AnimationDto();


    let id = this.route.snapshot.params['id'];

    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }
    );

    this.animationService.subjectMiseAJour.subscribe(
      res => {
        this.animationService.getAll().subscribe(
          donnees => {
            this.animations = donnees;
          }
        );
      }
    );

    this.animationService.getAll().subscribe(
      resultat => {
        this.animations = resultat;
      }
    );

    this.salleService.subjectMiseAJour.subscribe(
      res => {
        this.salleService.getAll().subscribe(
          donnees => {
            this.salles = donnees;
          }
        );
      }
    );

    this.salleService.getAll().subscribe(
      resultat => {
        this.salles = resultat;
      }
    );



  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.manifestationService.update(id, this.manifestation).subscribe(
      res => {
        console.log("Modification Ok");
        this.goHome();
      }
    );
  }

  onSubmit() {
    this.update();
  }

  goHome() {
    this.router.navigate(['/manifestation-list']);
  }

}


