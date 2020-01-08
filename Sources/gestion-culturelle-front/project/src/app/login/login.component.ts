import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.user=new User();
  }

  login(){
    if(this.authService.login(this.user)){
      this.router.navigateByUrl('/');
    }
  }

}
