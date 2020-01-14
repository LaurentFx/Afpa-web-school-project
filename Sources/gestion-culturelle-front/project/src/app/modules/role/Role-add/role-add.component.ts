import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RoleDto } from 'src/app/model/roleDto';
import { RoleService } from 'src/app/service/role.service';

@Component({
  selector: 'app-role-add',
  templateUrl: './role-add.component.html',
  styleUrls: ['./role-add.component.css']
})
export class RoleAddComponent implements OnInit {
_
    role: RoleDto;   
  
    constructor(private roleService: RoleService, private router: Router) { }
  
    ngOnInit() {
      this.role = new RoleDto();  
    }
  
    ajoutRole(): void {
      this.roleService.ajoutRole(this.role).subscribe(
        res => {
          this.roleService.subjectMiseAJour.next(0);
          console.log("Ajout Ok");
          this.goHome();
        }
  
      ); 
      this.role = new RoleDto();    
    }
  
    goHome() {
      this.router.navigate(['/role-list']);
  
    }
  }


