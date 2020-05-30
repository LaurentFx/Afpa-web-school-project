import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../security/auth.service';
import { ToastrService } from 'ngx-toastr';
import { faTrashAlt, faHome, faCheckCircle, faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { InvitationService } from '../../../service/invitation.service';
import { InvitationDto } from '../../../model/invitationDto';
import { User } from '../../../model/user';

@Component({
  selector: 'app-invitation-answer',
  templateUrl: './invitation-answer.component.html',
  styleUrls: ['./invitation-answer.component.css']
})
export class InvitationAnswerComponent implements OnInit {
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faCheckCircle = faCheckCircle;
  faTimesCircle = faTimesCircle;
  userCourant: User;
  isAnswered:boolean;

  invitation: InvitationDto;
  invitations: InvitationDto[];


  constructor(private manifestationService: ManifestationService, private router: Router,
    private invitationService: InvitationService, private authService: AuthService, private toastrService: ToastrService) { }


  ngOnInit() {
    const userCourant = this.authService.getCurrentUser();
   this.invitation = new InvitationDto();

    this.invitationService.subjectMiseAJour.subscribe(
      res => {
        this.invitationService.getNewByUser(userCourant.id).subscribe(
          donnees => {
            this.invitations = donnees;
            if (this.invitations.length===0) {
              this.toastrService.info('Liste vide : vous avez répondu à toutes vos invitations.', 'Information');
              this.router.navigate(['/invitation-show/'+userCourant.id]);
            }
          }
        );
      }
    );

    this.invitationService.getNewByUser(userCourant.id).subscribe(
      resultat => {
        this.invitations = resultat;
        if (this.invitations.length===0) {
          this.toastrService.info('Liste vide : Vous avez répondu à toutes vos invitations.', 'Information');
          this.router.navigate(['/invitation-show/'+userCourant.id]);
        }
      }
    );


  }

  oui(id: number) {
  
    this.invitationService.updateReponse(id, 'Oui').subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réponse à l invitation N°'+id + ' a été modifiée.', 'Réponse Ok')
        } else {
          this.toastrService.error('La réponse à l invitation N°'+id +  ' n a pas été modifiée', 'Réponse NOk')
        }
      }
    );
    this.ngOnInit();
  }

  non(id: number) {
  
    this.invitationService.updateReponse(id, 'Non').subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réponse à l invitation N°'+id + ' a été modifiée.', 'Réponse Ok.')
        } else {
          this.toastrService.error('La réponse à l invitation N°'+id +  ' n a pas été modifiée', 'Réponse NOk.')
        }
      }
    );
    this.ngOnInit();
  }



}


