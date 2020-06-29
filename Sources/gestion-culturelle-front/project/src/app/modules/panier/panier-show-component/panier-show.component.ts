/* import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticleDto } from '../../../model/articleDto';
import { PanierDto } from '../../../model/panierDto';
import { User } from '../../../model/user';
import { ArticleService } from '../../../service/article.service';
import { PanierService } from '../../../service/panier.service';
import { AuthService } from '../../../security/auth.service';
import { UserService } from '../../../service/user.service';
import { RoleDto } from '../../../model/roleDto';
import { ToastrService } from 'ngx-toastr';
import { faHome, faCalendarPlus,faCalendarCheck,faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-panier-show',
  templateUrl: './panier-show.component.html',
  styleUrls: ['./panier-show.component.css']
})
export class PanierShowComponent implements OnInit {

  listArticles: ArticleDto[];
  panierDto: PanierDto;
  userDto: User;
  id: number;
  faHome = faHome;
  faCalendarPlus = faCalendarPlus;
  faCalendarCheck = faCalendarCheck;
  faTrashAlt = faTrashAlt;

  constructor(private articleService: ArticleService, private toastrService: ToastrService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService, private userService: UserService,
  ) { this.userDto = new User(); this.panierDto = new PanierDto(); }


  ngOnInit() {
    this.reload();
  }

  reload() {
    let idUser = this.authService.getCurrentUser().id;

    this.userService.getOne(idUser).subscribe(
      res => {
        this.userDto = res;
        this.panierDto = res.panier;
      }
    );

    this.id = this.route.snapshot.params['id'];
    this.articleService.getArticles(this.id).subscribe(
      donnees => {
        this.listArticles = donnees;

      }
    );

  }

  valid(id: number) {
    this.id = this.route.snapshot.params['id'];
    this.panierService.deletePanier(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réservation a été validée', 'Validation Ok')
        } else {
          this.toastrService.error('La réservation n a pas été validée', 'Validation NOk')
        }
        this.panierService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
  }

  cancel(id: number) {
    this.id = this.route.snapshot.params['id'];
    this.panierService.deleteArticles(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('Les réservations ont été annulées', 'Annulation Ok')
        } else {
          this.toastrService.error('Les réservations n ont pas été annulées', 'Annulation NOk')
        }
        this.panierService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
  }

  delete(id: number) {
    this.id = this.route.snapshot.params['id'];
    this.articleService.delete(id).subscribe(
      res => {
        if (res) {
          this.toastrService.success('La réservation a été annulée', 'Annulation Ok')
        } else {
          this.toastrService.error('La réservation n a pas été annulée', 'Annulation NOk')
        }
        this.articleService.subjectMiseAJour.next(0);
        this.reload()
      }
    )
  }

  goHome() {
    this.router.navigateByUrl('/public')
  }

}
 */