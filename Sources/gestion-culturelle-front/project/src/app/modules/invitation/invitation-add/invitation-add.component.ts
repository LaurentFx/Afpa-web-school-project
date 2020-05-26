import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { InvitationDto } from '../../../model/invitationDto';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
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
  vips: User[];
  invits: User[];
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
    this.invitationDto = new InvitationDto();
    this.manifestationDto.listVips = new User();

    this.reload();
  }

  reload() {

    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
        this.manifestationService.subjectMiseAJour.next(0);
      }
    )

        this.userService.getByRole(5).subscribe(
          res => {
            this.vips = res;
            this.userService.subjectMiseAJour.next(0);
          }
        );

    /*  this.invitationService.getVips(this.route.snapshot.params['id']).subscribe(
        res => {
          this.vips = res;
        }
      ); */

 //   this.invitationService.subjectMiseAJour.subscribe(/
     // res => {
        this.invitationService.getByManifestation(this.route.snapshot.params['id']).subscribe(
          res => {
            this.invits = res;
            this.invitationService.subjectMiseAJour.next(0);
          }
        );
   //   }
  //  );

  }


  /*
  addVips(idVip: number): void {
    this.manifestationDto.listVips.id = idVip;
    this.invitationService.updateAdd(idVip, this.manifestationDto).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );
    this.reload();
  } */

  addInvitation(idVip: number): void {
    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    );

    this.userService.getOne(idVip).subscribe(
      resu => {
        this.userDto = resu;
      }
    );

    this.invitationDto.manifestation = this.manifestationDto;
    this.invitationDto.vip = this.userDto;
    this.invitationService.add(this.invitationDto).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );

    this.reload();
  }

  subInvitation(idInvitation: number): void {
    this.invitationService.delete(idInvitation).subscribe(
      res => {
        this.invitationService.subjectMiseAJour.next(0);
      }
    );

    this.reload();
  }

  /*
   subVips(idVip: number): void {
     //   this.manifestationtmp = this.manifestationDto;
     //  console.log('manifestationDto id 2 ' + this.manifestationtmp.id);
     //   this.manifestationtmp.listVips.id = idVip;
     console.log('ListVips id ' + idVip);
 
     this.invitationService.updateSub(idVip, this.manifestationDto).subscribe(
       res => {
         this.invitationService.subjectMiseAJour.next(0);
       }
     );
     this.reload();
   } */

  valid() {
    this.router.navigateByUrl('/public')
  }

  cancel() {
    this.invitationService.deleteAll(this.route.snapshot.params['id']).subscribe(
      res => {
        // toaster
      }
    );
    this.router.navigateByUrl('/public')
  }
}
