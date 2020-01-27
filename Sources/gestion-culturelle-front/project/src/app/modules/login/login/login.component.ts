import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { Router } from '@angular/router';
import { UserAuth } from '../../../model/user-auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserAuth;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.user=new UserAuth();
    this.user.username='';
    this.user.password = '';
  }

  login(){
    this.authService.login(this.user).subscribe(res=>{
      if(res){
        console.log('connexion Ok')
        this.router.navigateByUrl('/public');      
      } else {
        console.log('connexion NOk')
      }
    });
  }

}
