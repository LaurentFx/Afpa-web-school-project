import { Component, OnInit } from "@angular/core";
import { RoleDto } from "../../../model/roleDto";
import { Router } from "@angular/router";
import { RoleService } from "../../../service/role.service";
import { faInfoCircle, faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {

  faInfoCircle =faInfoCircle;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;

  roles: RoleDto[];
  role: RoleDto;
  
  constructor(private roleService: RoleService,
    private router: Router, 
    private toastrService:ToastrService) { }

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

  delete(id: number) {
    this.role = new RoleDto();

    this.roleService.getOne(id).subscribe(
      res=>{
        this.role = res
        console.log(res);
        console.log('delete Ok ');
      }
    )

    this.roleService.delete(id).subscribe(
      res=>{
        this.roleService.subjectMiseAJour.next(0)
        if(res){
          this.toastrService.success(this.role.label+' effacé.','Suppression Ok.')
        }else{
          this.toastrService.error('Le role '+this.role.label+' est associé à une user')
        }
      }
    )
  }
  
  redirectToUpdate(id: number){
    this.router.navigateByUrl('/role-update/'+id)
  }
   

  redirectToShow(id: number) {
    this.router.navigateByUrl('/role-show/'+id)
  }

}
