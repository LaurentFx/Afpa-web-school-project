import { Component, OnInit } from '@angular/core';
import { User } from '../../..//model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RoleService } from '../../../service/role.service';
import { RoleDto } from '../../../model/roleDto';
import { faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  user: User;
  roles: RoleDto[];
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  
  constructor(private userService: UserService, 
    private router: Router,
    private roleService: RoleService,
     private toastrService: ToastrService) { }

  ngOnInit() {
    this.roles= [];
    this.user = new User();
    this.user.role = new RoleDto();
    this.roleService.getAll().subscribe(
      res=>{
        this.roles = res;
      }
    );
  }

  add() : void {
    let nom = this.user.nom;
    this.userService.add(this.user).subscribe(
        res => {
          if (res===0) {
            this.toastrService.error('Le user '+nom +' existe déjà', 'Ajout impossible')
          } else {
            this.toastrService.success('Nouveau user : ' +nom, 'Ajout Ok')
          }
        // this.userService.subjectMiseAJour.next(0);
          this.goHome();
      }
    );
  }


  goHome() {
    this.router.navigate(['/user-list']);
  }

}
