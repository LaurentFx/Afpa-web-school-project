import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { InvitationDto } from '../../../model/invitationDto';
import { ToastrService } from 'ngx-toastr';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../security/auth.service';
import { UserService } from '../../../service/user.service';
import { InvitationService } from '../../../service/invitation.service';
import { faHome, faPlusSquare, faMinusSquare, faCheckSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-invitation-add',
  templateUrl: './invitation-add.component.html',
  styleUrls: ['./invitation-add.component.css']
})
export class InvitationAddComponent implements OnInit {
  manifestationDto: ManifestationDto;
  userDto: User;
  invitationDto: InvitationDto;
  invites: User[];
  invitations: User[];
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  faMinusSquare = faMinusSquare;
  faCheckSquare = faCheckSquare;
  addInvites: boolean;
  subInvites:boolean;

  constructor(private manifestationService: ManifestationService,
    private route: ActivatedRoute, private router: Router,
    private authService: AuthService, private userService: UserService,
    private invitationService: InvitationService, private toastrService: ToastrService
  ) {

  }

  ngOnInit() {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
    this.invitationDto = new InvitationDto();
    this.invitationDto.manifestation = new ManifestationDto();
    this.invitationDto.vip = new User();
    this.addInvites = false;
    this.subInvites = true;
    this.reload();
  }

  reload() {
    this.addInvites = false;
    this.subInvites = true;
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
        this.manifestationDto.reservationsVip = resu.reservationsVip;
        if (this.manifestationDto.reservationsVip > 0) {
          this.addInvites = true;
        }
        if (this.manifestationDto.reservationsVip === 0) {
          this.toastrService.info('Nombre d invitations Max atteint', 'Information');
        }
        this.manifestationService.subjectMiseAJour.next(0);
      }
    )

    this.userService.getInvites(this.route.snapshot.params['id']).subscribe(
      res => {
        this.invites = res;
        this.userService.subjectMiseAJour.next(0);
      }
    );

    this.invitationService.getByManifestation(this.route.snapshot.params['id']).subscribe(
      res => {
        this.invitations = res;
        this.invitationService.subjectMiseAJour.next(0);
        if (this.invitations.length === 0) {
          this.subInvites = false;
        }
      }
    );
  }

  addInvitation(idVip: number): void {
    this.invitationDto.manifestation.id = this.route.snapshot.params['id'];
    this.invitationDto.vip.id = idVip;

    this.invitationService.add(this.invitationDto).subscribe(
      res => {
        if (res) {
          this.toastrService.error('L invitation existe déjà', 'Ajout impossible')
        } else {
          this.toastrService.success('Invitation ajoutée.', 'Ajout Ok')
        }
        this.invitationService.subjectMiseAJour.next(0);

      }
    );
    this.reload();

  }

  subInvitation(idInvitation: number): void {
    this.invitationService.delete(idInvitation).subscribe(
      resp => {
        if (resp) {
          this.toastrService.success('Invitation effacée', 'Suppression Ok')
        } else {
          this.toastrService.error('L invitation n existe pas.', 'Suppression impossible')
        }
        this.invitationService.subjectMiseAJour.next(0);
      }
    );

    this.reload();
  }


  valid() {
    this.router.navigateByUrl('/public')
  }

  cancel() {
    this.invitationService.deleteAll(this.route.snapshot.params['id']).subscribe(
      res => {
        if (res) {
          this.toastrService.success('Les invitations ont été annulées', 'Annulation Ok')
        } else {

        }
        this.invitationService.subjectMiseAJour.next(0);
      }
    );
    this.router.navigateByUrl('/public')
  }
}
