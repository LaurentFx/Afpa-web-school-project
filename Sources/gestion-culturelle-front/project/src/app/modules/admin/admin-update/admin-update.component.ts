import { Component, OnInit } from '@angular/core';
import { AdminDto } from 'src/app/model/adminDto';
import { RoleDto } from 'src/app/model/roleDto';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleService } from 'src/app/service/role.service';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-admin-update',
  templateUrl: './admin-update.component.html',
  styleUrls: ['./admin-update.component.css']
})
export class AdminUpdateComponent implements OnInit {

  id:number;
  admin: AdminDto;
  roles: RoleDto[];

  constructor(private route: ActivatedRoute, private roleService: RoleService,private adminService: AdminService, private router: Router) { }

  ngOnInit() {
    this.roles = [];
    this.admin = new AdminDto();
    this.admin.role=new RoleDto();

    let id = this.route.snapshot.params['id'];

    this.adminService.getOne(id).subscribe(
      res => {
        this.admin = res;
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
    this.adminService.update(id, this.admin).subscribe(
      res => {
       console.log("Modification Ok");
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/admin-list']);
  }

}

