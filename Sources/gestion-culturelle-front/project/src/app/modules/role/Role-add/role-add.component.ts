import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { RoleDto } from '../../../model/roleDto';
import { RoleService } from '../../../service/role.service';

@Component({
  selector: 'app-role-add',
  templateUrl: './role-add.component.html',
  styleUrls: ['./role-add.component.css']
})
export class RoleAddComponent implements OnInit {
    role: RoleDto;   
  
    constructor(private roleService: RoleService,
      private toastrService: ToastrService,
       private router: Router) { }
  
    ngOnInit() {
      this.role = new RoleDto();  
    }
  
    add(): void {
      this.roleService.add(this.role).subscribe(
        res => {
          if (res===0) {
            this.toastrService.error('Le role '+this.role.label +' existe déjà', 'Ajout impossible')
          } else {
            this.toastrService.success('Nouveau role : ' +this.role.label, 'Ajout Ok')
          }
          this.goHome();
          this.roleService.subjectMiseAJour.next(0);
        }
  
      ); 
      this.role = new RoleDto();    
    }
  
    goHome() {
      this.router.navigate(['/role-list']);
  
    }
  }


