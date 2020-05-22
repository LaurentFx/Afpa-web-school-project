import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';
import { Router } from '@angular/router';
import { SalleDto } from '../../../model/salleDto';
import { AnimationDto } from '../../../model/animationDto';
import { AnimationService } from '../../../service/animation.service';
import { SalleService } from '../../../service/salle.service';
import { User } from '../../../model/user';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-manifestation-add',
  templateUrl: './manifestation-add.component.html',
  styleUrls: ['./manifestation-add.component.css']
})
export class ManifestationAddComponent implements OnInit {

  manifestation: ManifestationDto;
  salles: SalleDto[];
  animations: AnimationDto[];
  users: User[];
  salle: SalleDto;
  userCourant: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;

  constructor(private userService: UserService,   private salleService: SalleService,
    private animationService: AnimationService, private manifestationService: ManifestationService,
    private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.userCourant = this.authService.getCurrentUser();
    this.manifestation = new ManifestationDto();
    this.salle = new SalleDto();
    this.salles = [];
    this.animations = [];
    this.users = [];
    this.manifestation.salle = new SalleDto();
    this.manifestation.animation = new AnimationDto();
    this.manifestation.validateur = this.userCourant;

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

  }


  add(): void {
    this.manifestationService.add(this.manifestation).subscribe(
      res => {
        this.manifestationService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
    this.manifestation = new ManifestationDto();
    this.manifestation.validateur = new User();
    this.manifestation.animation = new AnimationDto();
    this.manifestation.salle = new SalleDto();

  }

  goHome() {
    this.router.navigate(['/public/manifestation-list']);

  }

}
