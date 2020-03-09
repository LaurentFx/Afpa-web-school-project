import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { faInfoCircle, faEdit, faTrashAlt, faHome, faPlusSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  
  faInfoCircle =faInfoCircle;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faHome = faHome;
  faPlusSquare = faPlusSquare;
  
  users: User[];
  rolee: string;

  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
    this.users=[];
    this.userService.getAll().subscribe(
      donnees =>{
        // this.users = donnees; 
        for (let index = 0; index < donnees.length; index++) {
          console.log(donnees[index].role)
          if(donnees[index].role.label === this.rolee){
            this.users.push(donnees[index]);
          }
           

          
        }
      }
    );

    this.userService.subjectMiseAJour.subscribe(
      res=>{
        this.userService.getAll().subscribe(
          donnees =>{
            this.users = donnees; 
          }
        );
      }
    );
  }

  test(){
    console.log(this.rolee)
    // this.router.navigateByUrl('')
    this.userService.getAll().subscribe(
      donnees =>{
        // this.users = donnees; 
        for (let index = 0; index < donnees.length; index++) {
          console.log(donnees[index].role)
          if(donnees[index].role.label === this.rolee){
            this.users= [];
            this.users.push(donnees[index]);
          }
        }
      }
    );
    this.userService.subjectMiseAJour.subscribe(
      res=>{
        this.userService.getAll().subscribe(
          donnees =>{
            this.users = donnees; 
          }
        );
      }
    );
  }

  delete(id:number) {
    this.userService.delete(id).subscribe(
      res=>{
        this.userService.subjectMiseAJour.next(0);
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/user-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/user-show/'+id)
  }

  redirectToRole(id:number){
    this.router.navigateByUrl('user-role-list'+id)
  }


}
