import { Component, OnInit } from '@angular/core';
import { AdminDto } from 'src/app/model/adminDto';
import { AdminService } from 'src/app/service/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {

  admins: AdminDto[];
  
  constructor(private adminService: AdminService,private router: Router) { }

  ngOnInit() {

    this.adminService.subjectMiseAJour.subscribe(
      res=> {
        this.adminService.getAll().subscribe(
          donnees =>{
			  this.admins = donnees; 
          }
        );
      }
    );

    this.adminService.getAll().subscribe(
      resultat =>{
          this.admins = resultat; 
        
      }
    );
  }

 delete(id:number) {
    this.adminService.delete(id).subscribe(
      res=>{
        this.adminService.subjectMiseAJour.next(0);
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/admin-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/admin-show/'+id)
  }

}
