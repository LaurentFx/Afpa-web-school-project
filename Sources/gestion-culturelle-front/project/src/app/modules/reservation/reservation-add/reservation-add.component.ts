import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
//import { ArticleDto } from '../../../model/articleDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../../model/user';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../security/auth.service';
import { UserService } from '../../../service/user.service';
import { faHome, faCalendarPlus } from '@fortawesome/free-solid-svg-icons';
import { ReservationDto } from '../../../model/reservationDto';
import { ReservationService } from '../../../service/reservation.service';

@Component({
  selector: 'app-reservation-add-component',
  templateUrl: './reservation-add.component.html',
  styleUrls: ['./reservation-add.component.css']
})
export class ReservationAddComponent implements OnInit {
  manifestationDto: ManifestationDto;
  userDto: User;
  reservationDto: ReservationDto;
  quantite: number;
  faHome = faHome;
  faCalendarPlus = faCalendarPlus;
  today: number = Date.now();

  //inutiles ?
  idUser: number;
  idManif: number;

  constructor(private manifestationService: ManifestationService,
    private route: ActivatedRoute,
    private router: Router, private authService: AuthService,
    private reservationService: ReservationService,
    private userService: UserService, private toastrService: ToastrService) {
  }

  ngOnInit() {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
    this.reservationDto = new ReservationDto();
    this.reservationDto.manifestation = new ManifestationDto();
    this.reservationDto.client = new User();

    this.userDto = this.authService.getCurrentUser();
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    )

   // this.reload();

  }

 /*  reload() {

    this.userDto = this.authService.getCurrentUser();
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    )
  }
 */

  add(): void {
    this.reservationDto.manifestation.id = this.route.snapshot.params['id'];
    this.reservationDto.client.id = this.authService.getCurrentUser().id;
    this.reservationDto.quantite = this.quantite;

    this.reservationService.add(this.reservationDto).subscribe(
      res => {
        if (res === 0) {
          this.toastrService.error('La reservation existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Reservation ajoutée.', 'Ajout Ok')
        }
        this.reservationService.subjectMiseAJour.next(0);
        this.goHome(this.userDto.id);
      }
    );

  }


  goHome(id: number) {
    this.router.navigateByUrl('/reservation-show/' + id)
  }

}