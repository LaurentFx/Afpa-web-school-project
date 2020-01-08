import { Component, OnInit } from '@angular/core';
import { RoleService } from '../../../service/role.service';

@Component({
  selector: 'app-role-add',
  templateUrl: './role-add.component.html',
  styleUrls: ['./role-add.component.css']
})
export class RoleAddComponent implements OnInit {

 role: string;

  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.role='';
  }

  add() : void {
    this.roleService.add(this.role).subscribe(
      res=>{
        this.roleService.subjectMiseAJour.next(0);
        console.log("ajout avec succes! ");
      }
    );
    this.role='';
  }

}
