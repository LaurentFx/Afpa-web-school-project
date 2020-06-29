import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ManifestationService } from '../../../service/manifestation.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SalleDto } from '../../../model/salleDto';
import { AnimationDto } from '../../../model/animationDto';
import { AnimationService } from '../../../service/animation.service';
import { SalleService } from '../../../service/salle.service';
import { User } from '../../../model/user';
import { AuthService } from '../../../security/auth.service';
import { UserService } from '../../../service/user.service';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-manifestation-add',
  templateUrl: './manifestation-add.component.html',
  styleUrls: ['./manifestation-add.component.css']
})
export class ManifestationAddComponent implements OnInit {

  manifestation: ManifestationDto;
  animations: AnimationDto[];
  userCourant: User;
  faHome = faHome;
  faPlusSquare = faPlusSquare;

  constructor(private userService: UserService,   private salleService: SalleService,
    private animationService: AnimationService, private manifestationService: ManifestationService,
    private authService: AuthService, private router: Router,private toastrService: ToastrService) { }

  ngOnInit() {
    this.userCourant = this.authService.getCurrentUser();
    this.manifestation = new ManifestationDto();
    this.animations = [];
   // inutile ?
    this.manifestation.animation = new AnimationDto();
    this.manifestation.validateur = this.userCourant;

    this.animationService.subjectMiseAJour.subscribe(
      res => {
        this.animationService.getAnimations().subscribe(
          donnees => {
            this.animations = donnees;
          }
        );
      }
    );

    this.animationService.getAnimations().subscribe(
      resultat => {
        this.animations = resultat;
      }
    );

  }


  add(): void {
    let nom = this.manifestation.label;
    this.manifestationService.add(this.manifestation).subscribe(
      res => {
        if (res===0) {
          this.toastrService.error('La manifestation '+nom +' existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Nouvelle manifestation : ' +nom, 'Ajout Ok')
        }
        this.manifestationService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
    this.manifestation = new ManifestationDto();
    this.manifestation.validateur = new User();
    this.manifestation.animation = new AnimationDto();

  }

  goHome() {
    this.router.navigate(['/public/manifestation-list']);

  }

}
