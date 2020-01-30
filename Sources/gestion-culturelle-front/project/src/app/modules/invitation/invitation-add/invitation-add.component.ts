import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';
import { Observable } from 'rxjs';
import { InvitationService } from '../../../service/invitation.service';

@Component({
  selector: 'app-invitation-add',
  templateUrl: './invitation-add.component.html',
  styleUrls: ['./invitation-add.component.css']
})
export class InvitationAddComponent implements OnInit {
  manifestationDto: ManifestationDto;
  userDto: User;
  vip: User;
  vips: User[];

  constructor(private manifestationService: ManifestationService,
    private route: ActivatedRoute, private router: Router,
    private authService: AuthService, private userService: UserService,
    private invitationService: InvitationService,

  ) {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
  }

  ngOnInit() {
    this.reload();
  }


  reload() {

    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    )

    this.invitationService.getAll().subscribe(
      res => {
        this.vips = res;
      }
    );





  }


  goHome() {

    this.router.navigateByUrl('/public')

  }
}
