import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../service/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../../model/user';
import { RoleDto } from '../../../model/roleDto';
import { RoleService } from '../../../service/role.service';
import { ToastrService } from 'ngx-toastr';
import { faHome, faCheckSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  id: number;
  user: User;
  roles: RoleDto[];
  faHome = faHome;
  faCheckSquare = faCheckSquare;

  constructor(private route: ActivatedRoute, private userService: UserService,
    private router: Router, private roleService: RoleService,
    private toastrService: ToastrService) { }

  ngOnInit() {
    this.roles = [];
    this.user = new User();
    this.user.role = new RoleDto();

    let id = this.route.snapshot.params['id'];

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    );

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

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.userService.update(id, this.user).subscribe(
      res => {
        if (res) {
          this.toastrService.success(this.user.nom+' a été modifié.','Mise à jour Ok.')
        } else {
          this.toastrService.error(+this.user.nom+' n a pas été modifié','Mise à jour impossible.')
        }
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/user-list']);
  }





}
