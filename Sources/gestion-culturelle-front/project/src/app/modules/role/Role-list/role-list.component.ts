import { Component, OnInit } from '@angular/core';
import { RoleDto } from 'src/app/model/roleDto';
import { RoleService } from 'src/app/service/role.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {
  roles: RoleDto[];
  
  constructor(private roleService: RoleService,private router: Router) { }

  ngOnInit() {

    this.roleService.subjectMiseAJour.subscribe(
      res=> {
        this.roleService.getAll().subscribe(
          donnees =>{
			  this.roles = donnees; 
          }
        );
      }
    );

    this.roleService.getAll().subscribe(
      resultat =>{
          this.roles = resultat; 
               }
    );
  }

  delete(id:number) {
    this.roleService.delete(id).subscribe(
      res=>{
        this.roleService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/role-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/role-show/'+id)
  }

}
