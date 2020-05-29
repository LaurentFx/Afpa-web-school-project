import { Component, OnInit } from '@angular/core';
import { ManifestationDto } from '../../../model/manifestationDto';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { RoleDto } from '../../../model/roleDto';
import { ToastrService } from 'ngx-toastr';
import { faInfoCircle, faEdit, faTrashAlt, faHome, faPlusSquare, faCalendarPlus, faUserTie } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-manifestation-list',
  templateUrl: './manifestation-list.component.html',
  styleUrls: ['./manifestation-list.component.css']
})
export class ManifestationListComponent implements OnInit {

  faInfoCircle =faInfoCircle;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  faCalendarPlus = faCalendarPlus;
  faUserTie = faUserTie;

  isConnected: boolean;
  manifestation: ManifestationDto;
  manifestations: ManifestationDto[];
  isResp: boolean;
  isAdmin:boolean;
  isClient: boolean;
  isRespAdmin: boolean;
  user: String;
  role: RoleDto;

  constructor(private manifestationService: ManifestationService, private router: Router,
    private authService: AuthService, private toastrService: ToastrService) { }


  ngOnInit() {

    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
      this.isRespAdmin = (this.authService.getCurrentUser().role.label === 'RESP') || (this.authService.getCurrentUser().role.label === 'ADMIN');
      this.isClient = this.authService.getCurrentUser().role.label === 'CLIENT';
      this.user = this.authService.getCurrentUser().nom;
      this.role = this.authService.getCurrentUser().role;
    }

    this.manifestationService.subjectMiseAJour.subscribe(
      res => {
        this.manifestationService.getAll().subscribe(
          donnees => {
            this.manifestations = donnees;
          }
        );
      }
    );

    this.manifestationService.getAll().subscribe(
      resultat => {
        this.manifestations = resultat;

      }
    );

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

      }
    );

  }

  delete(id: number) {
    this.manifestation = new ManifestationDto();
    this.manifestationService.getOne(id).subscribe(
      res => {
        this.manifestation = res;
      }

    );

    this.manifestationService.delete(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success(this.manifestation.label + ' effacée.', 'Suppression Ok.')
        } else {
          this.toastrService.error('La manifestation ' + this.manifestation.label+ ' non effacée.', 'Suppression impossible')
        }
        this.manifestationService.subjectMiseAJour.next(0);
      }
    )
  }

  redirectToUpdate(id: number) {
    this.router.navigateByUrl('/manifestation-update/' + id)
  }

  redirectToShow(id: number) {
    this.router.navigateByUrl('/manifestation-show/' + id)
  }

  redirectToAddPanier(id: number) {
    this.router.navigateByUrl('/panier-ad/' + id)
  }

  redirectToInvit(id: number) {
    this.router.navigateByUrl('/invitation-ad/' + id)
  }

}

