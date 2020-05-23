import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';
import { InvitationService } from '../../../service/invitation.service';
import { faHome, faPlusSquare,faMinusSquare,faCheckSquare } from '@fortawesome/free-solid-svg-icons';

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
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  faMinusSquare = faMinusSquare;
  faCheckSquare = faCheckSquare;

  constructor(private manifestationService: ManifestationService,
    private route: ActivatedRoute, private router: Router,
    private authService: AuthService, private userService: UserService,
    private invitationService: InvitationService,

  ) {
    
  }

  ngOnInit() {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
    this.manifestationtmp = new ManifestationDto();
    this.manifestationDto.listVips = new User ();

    this.reload();
  }

  reload() {
    //let manif = this.manifestationDto.id;
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    )

    this.invitationService.getVips(this.route.snapshot.params['id']).subscribe(
      res => {
        this.vips = res;
      }
    );

    this.invitationService.getListVips(this.route.snapshot.params['id']).subscribe(
      res => {
        this.invits = res;
      }
    );
  }

  addVips(idVip: number): void {
   /*  this.manifestationtmp = this.manifestationDto; */
    console.log('manifestationDto id 2 ' + this.manifestationDto.id);
    this.manifestationDto.listVips.id = idVip;
    console.log('ListVips id ' + idVip);

    this.invitationService.updateAdd(idVip, this.manifestationDto).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );
    this.reload();
  }

  subVips(idVip: number): void {
    this.manifestationtmp = this.manifestationDto;
    console.log('manifestationDto id 2 ' + this.manifestationtmp.id);
    this.manifestationtmp.listVips.id = idVip;
    console.log('ListVips id ' + idVip);

    this.invitationService.updateSub(idVip, this.manifestationtmp).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );
    this.reload();
  }

  add() {
    alert("A finir");
  }

  goHome() {

    this.router.navigateByUrl('/public')

  }
}
