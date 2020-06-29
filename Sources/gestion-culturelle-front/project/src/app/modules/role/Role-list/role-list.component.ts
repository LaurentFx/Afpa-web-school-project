import { Component, OnInit } from "@angular/core";
import { RoleDto } from "../../../model/roleDto";
import { Router } from "@angular/router";
import { RoleService } from "../../../service/role.service";
import { ToastrService } from 'ngx-toastr';
import { faInfoCircle, faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from "../../../security/auth.service";


@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {
  faInfoCircle = faInfoCircle;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  role: RoleDto;
  roles: RoleDto[];
  isResp: boolean;

  constructor(private roleService: RoleService, private router: Router,
    private authService: AuthService, private toastrService: ToastrService) { }

  ngOnInit() {
    this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
    this.roleService.subjectMiseAJour.subscribe(
      res => {
        this.roleService.getAll().subscribe(
          donnees => {
            this.roles = donnees;
          }
        );
      }
    );

    this.roleService.getAll().subscribe(
      resultat => {
        this.roles = resultat;
      }
    );
  }

  redirectToUpdate(id: number) {
    this.router.navigateByUrl('/role-update/' + id)
  }


  redirectToShow(id: number) {
    this.router.navigateByUrl('/role-show/' + id)
  }

}
