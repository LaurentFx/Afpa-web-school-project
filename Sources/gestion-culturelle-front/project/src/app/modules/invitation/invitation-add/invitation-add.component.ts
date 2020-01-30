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
  invits: User[];
  manifestationtmp: ManifestationDto;

  constructor(private manifestationService: ManifestationService,
    private route: ActivatedRoute, private router: Router,
    private authService: AuthService, private userService: UserService,
    private invitationService: InvitationService,

  ) {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
    this.manifestationtmp = new ManifestationDto();
  }

  ngOnInit() {
    this.reload();
  }


  reload() {
    //let manif = this.manifestationDto.id;
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
        //manif=resu.id;
      }
    )

    console.log('manifestationDto id ' + this.route.snapshot.params['id']);
    this.invitationService.getVips(this.route.snapshot.params['id']).subscribe(
      res => {
        this.vips = res;
      }
    );

    /* this.invitationService.getOne(4).subscribe(
      res => {
        this.vips = res;
      }
    );
 */

    this.invitationService.getListVips(this.route.snapshot.params['id']).subscribe(
      res => {
        this.invits = res;
      }
    );
  }

  addVips(idVip: number): void {
    this.reload();
    this.manifestationtmp = this.manifestationDto;
    console.log('manifestationDto ' + this.manifestationtmp);
    this.manifestationtmp.listVips.id = idVip;
    console.log('ListVips ' + idVip);


    this.invitationService.update(idVip, this.manifestationtmp).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );
  }



  goHome() {

    this.router.navigateByUrl('/public')

  }
}
