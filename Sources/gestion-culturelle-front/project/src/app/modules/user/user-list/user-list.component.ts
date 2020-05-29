import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
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
  user: User;
  users: User[];

  constructor(private userService: UserService,private router: Router, private toastrService: ToastrService) { }

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
    this.user = new User ();

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    );

    this.userService.delete(id).subscribe(
      res=>{
        if (res) {
          this.toastrService.success(this.user.nom + ' effacé.', 'Suppression Ok.')
        } else {
          this.toastrService.error('L user ' + this.user.nom + ' est lié à une animation, invitation ou réservation.', 'Suppression impossible')
        }
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
