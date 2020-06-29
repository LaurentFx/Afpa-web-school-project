import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../security/auth.service';
import { ToastrService } from 'ngx-toastr';
import { faEdit, faTrashAlt, faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { InvitationService } from '../../../service/invitation.service';
import { InvitationDto } from '../../../model/invitationDto';

@Component({
  selector: 'app-invitation-list',
  templateUrl: './invitation-list.component.html',
  styleUrls: ['./invitation-list.component.css']
})
export class InvitationListComponent implements OnInit {

  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  isConnected: boolean;
  invitations: InvitationDto[];
  isAdmin:boolean;
  isRespAdmin: boolean;

  constructor(private manifestationService: ManifestationService, private router: Router,
    private invitationService: InvitationService,  private authService: AuthService, private toastrService: ToastrService) { }

  ngOnInit() {
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
      this.isRespAdmin = (this.authService.getCurrentUser().role.label === 'RESP') || (this.authService.getCurrentUser().role.label === 'ADMIN');
    }

    this.invitationService.subjectMiseAJour.subscribe(
      res => {
        this.invitationService.getAll().subscribe(
          donnees => {
            this.invitations = donnees;
          }
        );
      }
    );

    this.invitationService.getAll().subscribe(
      resultat => {
        this.invitations = resultat;
      }
    );

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();
      }
    );

  }

  delete(id: number) {
    
    this.invitationService.delete(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('Invitation '+id + ' effacée.', 'Suppression Ok.')
        } else {
          this.toastrService.error('Invitation '+id+ ' non effacée.', 'Suppression impossible')
        }
        this.invitationService.subjectMiseAJour.next(0);
      }
    )
    this.ngOnInit();
  }

 

}

