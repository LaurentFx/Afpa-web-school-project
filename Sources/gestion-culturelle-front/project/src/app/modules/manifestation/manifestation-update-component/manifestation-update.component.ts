import { Component, OnInit, Testability } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AnimationDto } from '../../../model/animationDto';
import { SalleDto } from '../../../model/salleDto';
import { SalleService } from '../../../service/salle.service';
import { AnimationService } from '../../../service/animation.service';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-manifestation-update',
  templateUrl: './manifestation-update.component.html',
  styleUrls: ['./manifestation-update.component.css']
})
export class ManifestationUpdateComponent implements OnInit {

  id: number;
  manifestation: ManifestationDto;
  animation: AnimationDto;
  animations: AnimationDto[];
  salle: SalleDto;
  salles: SalleDto[];
  users: User[];
 capacite: number;

  constructor(
    private userService: UserService,
    private salleService: SalleService,
    private animationService: AnimationService,
    private route: ActivatedRoute,
    private manifestationService: ManifestationService,
    private router: Router) { }

  ngOnInit() {
    this.manifestation = new ManifestationDto();
    this.animation = new AnimationDto();
    this.salles = [];
    this.animations = [];
    this.users = [];

    this.manifestation.salle = new SalleDto();
    this.manifestation.animation = new AnimationDto();
    this.manifestation.validateur = new User();
   //this.capacite = this.manifestation.salle.capacite;

    let id = this.route.snapshot.params['id'];

    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
        this.animation = this.manifestation.animation;
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
        this.salles.forEach(function (item, index, array) {
          console.log("salles " + item.capacite, index);
          //        console.log("nbreSpectateursPrevus 2 " + this.animation.nbreSpectateursPrevus);
        });
      }
    );


    this.userService.subjectMiseAJour.subscribe(
      res => {
        this.userService.getAll().subscribe(
          donnees => {
            this.users = donnees;
          }
        );
      }
    );

    this.userService.getAll().subscribe(
      resultat => {
        this.users = resultat;
      }
    );

//console.log("capacite "+this.capacite);

  }


  update(): void {
    let id = this.route.snapshot.params['id'];
    this.manifestationService.update(id, this.manifestation).subscribe(
      res => {
        console.log(this.manifestation);
        this.goHome();
      }
    );
  }

  onSubmit() {
    this.update();
  }

  goHome() {
    this.router.navigate(['/public/manifestation-list']);
  }

}


