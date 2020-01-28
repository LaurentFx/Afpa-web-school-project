import { Component, OnInit, ɵɵsanitizeUrlOrResourceUrl } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { RoleDto } from '../model/roleDto';
import { PanierDto } from '../model/panierDto';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navebar',
  templateUrl: './navebar.component.html',
  styleUrls: ['./navebar.component.css']
})
export class NavebarComponent implements OnInit {
  faInfoCircle =faInfoCircle;
  isConnected: boolean;
  isResp: boolean;
  isClient: boolean;
  isAnim: boolean;
  isAdmin: boolean;
  isRespAdmin: boolean;
  user: String;
  userDto: User;
  role: RoleDto;
  panier : PanierDto;
  panierDto: PanierDto;
  id:number;
  idPanier:number;

  constructor(private router: Router, private userService: UserService, private authService: AuthService  ) 
  {  this.panierDto = new PanierDto()}

  ngOnInit() {
    this.reload();
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isResp = this.authService.getCurrentUser().role.label === 'RESP';
      this.isClient = this.authService.getCurrentUser().role.label === 'CLIENT';
      this.isAnim = this.authService.getCurrentUser().role.label === 'ANIM';
      this.isAdmin = this.authService.getCurrentUser().role.label === 'ADMIN';
      this.isRespAdmin = (this.authService.getCurrentUser().role.label === 'RESP') || (this.authService.getCurrentUser().role.label === 'ADMIN');

      this.user = this.authService.getCurrentUser().nom;
      this.userDto = this.authService.getCurrentUser();
      this.role = this.authService.getCurrentUser().role;
      this.panierDto = this.authService.getCurrentUser().panier;
    }

    this.authService.subjectConnexion.subscribe(
      res => {
        this.isConnected = this.authService.isConnected();

        if (res == 0) {
          this.isResp = false;
          this.isClient = false;
          this.isAnim = false;
          this.isAdmin = false;
          this.isRespAdmin = false;
          this.user = '';
          this.role = null;
        } else {
          const userCourant = this.authService.getCurrentUser();
          this.isResp = userCourant.role.label === 'RESP';
          this.isClient = userCourant.role.label === 'CLIENT';
          this.isAnim = userCourant.role.label === 'ANIM';
          this.isAdmin = userCourant.role.label === 'ADMIN';
          this.isRespAdmin = (userCourant.role.label === 'RESP') || (userCourant.role.label === 'ADMIN');
          this.userDto = userCourant;
          this.user = userCourant.nom;
          this.role = userCourant.role;
          this.panierDto = userCourant.panier
        }
      }
    );
  }


  reload() {
    let idUser = this.authService.getCurrentUser().id;

    this.userService.getOne(idUser).subscribe(
      res => {
        this.userDto = res;
        this.panierDto = res.panier;
        this.idPanier = res.panier.id;
      }
    );
  }

  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('/public/login');
    this.isConnected = false;
  }

  redirectToShowPanier(id: number) {
    console.log('panier navebar' + this.panierDto.id);
    this.router.navigateByUrl('/panier-show/'+ id)

  }


  redirectToProfil(): void {
    this.router.navigateByUrl('/public/profil')

  }

}
