import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../security/auth.service';
import { Router } from '@angular/router';
import { UserAuth } from '../../../model/user-auth';
import { ToastrService } from 'ngx-toastr';
import { faCaretSquareRight } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserAuth;
  faCaretSquareRight = faCaretSquareRight;

  constructor(
    private authService: AuthService, 
    private router: Router,
   private toastrService: ToastrService
    ) { }

  ngOnInit() {
    this.user=new UserAuth();
    this.user.username='';
    this.user.password = '';
  }

  login(){
    this.authService.login(this.user).subscribe(res=>{
      if(res){
  //   this.toastrService.success('Bienvenue '+this.user.username,'Connexion Ok');
        this.router.navigateByUrl('/public'); 
      } else {
    //   this.toastrService.error('Connexion refusée')
      }
    });
  }

}
