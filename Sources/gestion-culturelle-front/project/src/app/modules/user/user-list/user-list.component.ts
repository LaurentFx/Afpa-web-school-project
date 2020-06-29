import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { faInfoCircle, faEdit, faTrashAlt, faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import { RoleDto } from '../../../model/roleDto';
import { AuthService } from '../../../security/auth.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  faInfoCircle = faInfoCircle;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  isResp:boolean;
  user: User;
  users: User[];

  constructor(private userService: UserService, private router: Router,
      private authService: AuthService, private toastrService: ToastrService) { }

  ngOnInit() {
    const userCourant = this.authService.getCurrentUser();
      this.isResp = userCourant.role.label === 'RESP';
    this.users = [];

    this.userService.getAll().subscribe(
      donnees => {
        this.users = donnees;
      }
    );

    this.userService.subjectMiseAJour.subscribe(
      res => {
        this.userService.getAll().subscribe(
          donnees => {
            this.users = donnees;
          }
        );
      }
    );

  }

  delete(id: number) {
    this.user = new User();
    this.user.role = new RoleDto();

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    );

    this.userService.delete(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success(this.user.nom + ' effacé.', 'Suppression Ok.')
        } else {
          if (this.user.role.label === 'ANIM') {
            this.toastrService.error(this.user.nom + ' est lié à une animation.', 'Suppression impossible')
          }
         else if (this.user.role.label === 'CLIENT') {
            this.toastrService.error(this.user.nom + ' est lié à une réservation.', 'Suppression impossible')
          }
         else if (this.user.role.label === 'VIP') {
            this.toastrService.error(this.user.nom + ' est lié à une invitation.', 'Suppression impossible')
          } else {
            this.toastrService.error(this.user.nom + ' est lié à une animation, invitation ou réservation.', 'Suppression impossible')
          }
        }
        this.userService.subjectMiseAJour.next(0);
      }
    )
  }

  redirectToUpdate(id: number) {
    this.router.navigateByUrl('/user-update/' + id)
  }

  redirectToShow(id: number) {
    this.router.navigateByUrl('/user-show/' + id)
  }

  redirectToRole(id: number) {
    this.router.navigateByUrl('user-role-list' + id)
  }


}
