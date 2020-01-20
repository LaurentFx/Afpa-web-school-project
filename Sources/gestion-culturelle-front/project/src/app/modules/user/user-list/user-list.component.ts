import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
    this.users=[];
    this.userService.getAll().subscribe(
      donnees =>{
        this.users = donnees; 
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
        console.log('delete Ok ');
      }
    )
  }
  
  redirectToUpdate(id:number){
    this.router.navigateByUrl('/user-update/'+id)
  }
   

  redirectToShow(id:number) {
    this.router.navigateByUrl('/user-show/'+id)
  }


}
