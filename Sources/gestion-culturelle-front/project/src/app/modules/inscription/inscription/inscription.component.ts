import { Component, OnInit } from '@angular/core';
import { User } from '../../..//model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { RoleService } from '../../../service/role.service';
import { RoleDto } from '../../../model/roleDto';


@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  user: User;
  role: RoleDto;

  constructor(private userService: UserService,
    private router: Router,
    private roleService: RoleService) { }

  ngOnInit() {
    this.user = new User();
    this.user.role = new RoleDto();

    this.roleService.getOne(4).subscribe(
      res => {
        this.role = res;
        console.log(res);
      }
    );
  }

  add(): void {
    this.userService.add(this.user).subscribe(
      res => {
        this.router.navigateByUrl('/user-list');
      }
    );
  }

}
