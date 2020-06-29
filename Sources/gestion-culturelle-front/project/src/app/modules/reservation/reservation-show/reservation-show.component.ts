import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
//import { ArticleDto } from '../../../model/articleDto';
import { User } from '../../../model/user';
import { AuthService } from '../../../security/auth.service';
import { UserService } from '../../../service/user.service';
import { ToastrService } from 'ngx-toastr';
import { faHome, faCalendarPlus, faCalendarCheck, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ReservationDto } from '../../../model/reservationDto';
import { ReservationService } from '../../../service/reservation.service';
import { ManifestationDto } from '../../../model/manifestationDto';

@Component({
  selector: 'app-reservation-show-component',
  templateUrl: './reservation-show.component.html',
  styleUrls: ['./reservation-show.component.css']
})
export class ReservationShowComponent implements OnInit {
  reservation: ReservationDto;
  reservations: ReservationDto[];
  manifestationDto: ManifestationDto;
  userDto: User;
  id: number;
  total: number;

  //listArticles: ArticleDto[];
 /*  panierDto: PanierDto; */
  faHome = faHome;
  faCalendarPlus = faCalendarPlus;
  faCalendarCheck = faCalendarCheck;
  faTrashAlt = faTrashAlt;

  constructor(private toastrService: ToastrService,  private reservationService: ReservationService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService, private userService: UserService,
  ) {   this.reservations = [];}


  ngOnInit() {
    this.userDto = new User();
    this.manifestationDto = new ManifestationDto();
    this.reservation = new ReservationDto();
    this.reservations = [];
    this.reload();
  }

  reload() {
    this.total = 0;
    this.reservations = [];
    const userDto = this.authService.getCurrentUser();
    this.id = this.route.snapshot.params['id'];

    this.reservationService.subjectMiseAJour.subscribe(
      res => {
        this.reservationService.getByUser(this.id).subscribe(
          donnees => {
            this.total = 0;
            this.reservations = donnees;
            for (let r of this.reservations) {
              this.total = this.total + (r.quantite*r.manifestation.prixBillet);
    
            }

          }
        );
      }
    );

    this.reservationService.getByUser(this.id).subscribe(
      donnees => {
        this.total = 0;
        this.reservations = donnees;
        for (let r of this.reservations) {
          this.total = this.total + (r.quantite*r.manifestation.prixBillet);

        }
      }
    );

    let idUser = this.authService.getCurrentUser().id;
    this.userService.getOne(idUser).subscribe(
      res => {
        this.userDto = res;
      //  this.panierDto = res.panier;
      }
    );
   /*  this.id = this.route.snapshot.params['id'];
    this.articleService.getArticles(this.id).subscribe(
      donnees => {
        this.listArticles = donnees;

      }
    ); */


  }

  /* valid(id: number) {
  //  this.id = this.route.snapshot.params['id'];
    this.reservationService.deletePanier(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réservation a été validée', 'Validation Ok')
        } else {
          this.toastrService.error('La réservation n a pas été validée', 'Validation NOk')
        }
        this.reservationService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
  }

  cancel(id: number) {
  //  this.id = this.route.snapshot.params['id'];
    this.reservationService.deleteArticles(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('Les réservations ont été annulées', 'Annulation Ok')
        } else {
          this.toastrService.error('Les réservations n ont pas été annulées', 'Annulation NOk')
        }
        this.reservationService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
  } */

  delete(id: number) {
    //  this.id = this.route.snapshot.params['id'];
    this.reservationService.delete(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réservation a été annulée', 'Annulation Ok')
        } else {
          this.toastrService.error('La réservation n a pas été annulée', 'Annulation NOk')
        }
        this.reservationService.subjectMiseAJour.next(0);
        this.reload()
      }
    )
  }

  goHome() {
    this.router.navigateByUrl('/public')
  }

}