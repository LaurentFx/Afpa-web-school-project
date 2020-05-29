import { Component, OnInit, Testability } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ActivatedRoute, Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AnimationDto } from '../../../model/animationDto';
import { SalleDto } from '../../../model/salleDto';
import { SalleService } from '../../../service/salle.service';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { faHome, faCheckSquare } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-manifestation-update',
  templateUrl: './manifestation-update.component.html',
  styleUrls: ['./manifestation-update.component.css']
})
export class ManifestationUpdateComponent implements OnInit {

  manifestation: ManifestationDto;
  animation: AnimationDto;
  salle: SalleDto;
  salles: SalleDto[];
  availability: any;
  faHome = faHome;
  faCheckSquare = faCheckSquare;

  constructor(
    private userService: UserService, private salleService: SalleService,
    private route: ActivatedRoute, private manifestationService: ManifestationService,
    private toastrService: ToastrService, private router: Router) {
    this.manifestation = new ManifestationDto();
    this.animation = new AnimationDto();
    this.salles = [];

    this.manifestation.salle = new SalleDto();
    this.manifestation.animation = new AnimationDto();
    this.manifestation.validateur = new User();

  }

  ngOnInit() {
    this.reload();

    /* this.manifestation = new ManifestationDto();
     this.animation = new AnimationDto();
     this.salles = [];
 
     this.manifestation.salle = new SalleDto();
     this.manifestation.animation = new AnimationDto();
     this.manifestation.validateur = new User();
    var nombre = this.manifestation.animation.nbreSpectateursPrevus;*/
  }

  reload() {
    let id = this.route.snapshot.params['id'];

    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }
    );

    this.salleService.getByCapacity(id).subscribe(
      resultat => {
        this.salles = resultat;
      }
    );

    this.salleService.subjectMiseAJour.subscribe(
      res => {
        this.salleService.getByCapacity(id).subscribe(
          donnees => {
            this.salles = donnees;
          }
        );
      }
    );

  
  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    console.log('this.availability 2'+this.availability);
    this.availability=this.checkAvalaibility();
    if (this.availability) {
      this.manifestationService.update(id, this.manifestation).subscribe(
      res => {
        this.toastrService.success('La manifestation a été mise à jour', 'Modification Ok.');
        this.goHome();
      }
    );
    }else {
      this.toastrService.error('La salle ' +this.manifestation.salle.label 
      + ' est occupée entre le'+this.manifestation.dateDebut+' et '+this.manifestation.dateFin, 'Disponibilité NOk')
      this.reload();
    }
    
  }

  checkAvalaibility(): any {
   // let res:boolean;
    this.manifestationService.getAvalaibility(this.manifestation).subscribe(
      res => {
        if (res) {
          this.toastrService.success('La salle ' + this.manifestation.salle.label 
          + ' est libre entre le'+this.manifestation.dateDebut+' et '+this.manifestation.dateFin, 'Disponibilité Ok.')
        } else {
          this.toastrService.error('La salle ' +this.manifestation.salle.label 
          + ' est occupée entre le'+this.manifestation.dateDebut+' et '+this.manifestation.dateFin, 'Disponibilité NOk')
        }
        this.availability=res;
      }
    );
    console.log('this.availability '+this.availability);
    return this.availability;
  }

 /*  onSubmit() {
    this.update();
  } */

  goHome() {
    this.router.navigate(['/public/manifestation-list']);
  }

}


