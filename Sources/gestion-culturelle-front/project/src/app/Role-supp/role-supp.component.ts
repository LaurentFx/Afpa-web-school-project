import { Component, OnInit } from '@angular/core';
import { RoleService } from '../service/Role.service';

@Component({
  selector: 'app-role-supp',
  templateUrl: './role-supp.component.html',
  styleUrls: ['./role-supp.component.css']
})
export class RoleASuppComponent implements OnInit {

 role: string;
 id: number;

  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.role='';
  }

  suppRole() : void {
    this.roleService.suppRole(this.id).subscribe(
      res=>{
        this.roleService.subjectMiseAJour.next(0);
        console.log("suppression avec succes! ");
      }
    );
    this.role='';
  }

}
