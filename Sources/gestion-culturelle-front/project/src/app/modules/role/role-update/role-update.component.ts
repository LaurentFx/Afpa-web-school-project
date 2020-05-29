import { Component, OnInit } from '@angular/core';
import { RoleService } from '../../../service/role.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleDto } from '../../../model/roleDto';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-role-update',
  templateUrl: './role-update.component.html',
  styleUrls: ['./role-update.component.css']
})
export class RoleUpdateComponent implements OnInit {

  id: number;
  role: RoleDto;

  constructor(private route: ActivatedRoute, private roleService: RoleService,
     private router: Router,    private toastrService: ToastrService) { }

  ngOnInit() {
    this.role = new RoleDto();

    let id = this.route.snapshot.params['id'];

    this.roleService.getOne(id).subscribe(
      res => {
        this.role = res;
      }
    );
  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.roleService.update(id,this.role).subscribe(
      res => {
        if (res) {
          this.toastrService.success(this.role.label+' a été modifiée.','Mise à jour Ok.')
        } else {
          this.toastrService.error(+this.role.label+' n a pas été modifiée','Mise à jour impossible.')
        }
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/role-list']);
  }

}
