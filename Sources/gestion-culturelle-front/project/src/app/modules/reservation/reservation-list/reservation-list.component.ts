import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../security/auth.service';
import { ToastrService } from 'ngx-toastr';
import { faEdit, faTrashAlt, faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { ReservationService } from '../../../service/reservation.service';
import { ReservationDto } from '../../../model/reservationDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { AnimationDto } from '../../../model/animationDto';


@Component({
  selector: 'app-reservation-list-component',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  userDto: User;
  reservations: ReservationDto[];
 // reservationDto:ReservationDto;

 // manifestationDto: ManifestationDto;
 // animationDto:AnimationDto;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  isConnected: boolean;
 
  isResp: boolean;
  isAdmin:boolean;
  isVip: boolean;
  isRespAdmin: boolean;

  constructor(private manifestationService: ManifestationService, private router: Router,
    private reservationService: ReservationService,  private authService: AuthService, private toastrService: ToastrService) { }

  ngOnInit() {
    /* this.userDto = new User();
    this.reservationDto.manifestation = new ManifestationDto();
    this.manifestationDto.animation = new AnimationDto(); */
    this.reservations = [];
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
      this.isRespAdmin = (this.authService.getCurrentUser().role.label === 'RESP') || (this.authService.getCurrentUser().role.label === 'ADMIN');
      this.isVip = this.authService.getCurrentUser().role.label === 'VIP';
    }
    this.userDto = this.authService.getCurrentUser()

    this.reservationService.subjectMiseAJour.subscribe(
      res => {
        this.reservationService.getAll().subscribe(
          donnees => {
            this.reservations = donnees;
          }
        );
      }
    );

    this.reservationService.getAll().subscribe(
      resultat => {
        this.reservations = resultat;
      }
    );

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();
      }
    );

  }
 

}


