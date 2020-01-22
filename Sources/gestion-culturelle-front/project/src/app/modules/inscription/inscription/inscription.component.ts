import { Component, OnInit } from '@angular/core';
import { User } from '../../..//model/user';
import { Router } from '@angular/router';
import { RoleService } from '../../../service/role.service';
import { RoleDto } from '../../../model/roleDto';
import { InscriptionService } from '../../../service/inscription.service';


@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  user: User;
  role: RoleDto;

  constructor(private inscriptionService: InscriptionService,
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
    this.inscriptionService.add(this.user).subscribe(
      res => {
        this.router.navigateByUrl('/public');
      }
    );
  }

}
