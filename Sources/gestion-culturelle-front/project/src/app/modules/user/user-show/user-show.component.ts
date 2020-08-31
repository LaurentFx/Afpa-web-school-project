import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';
import { RoleDto } from '../../../model/roleDto';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from '../../../security/auth.service';

@Component({
  selector: 'app-user-show',
  templateUrl: './user-show.component.html',
  styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {

  user: User;
  isClient:boolean;
  faHome = faHome;

    constructor(private userService: UserService,  private authService: AuthService,
    private router: Router,private route: ActivatedRoute ) { }

  ngOnInit() {
    this.user = new User ();
    const userCourant = this.authService.getCurrentUser();
    this.isClient = userCourant.role.label === 'CLIENT';
    this.user.role = new RoleDto();

    let id = this.route.snapshot.params['id'];

    this.userService.getOne(id).subscribe(
      res => {
        this.user = res;
      }
    );

  }

}
