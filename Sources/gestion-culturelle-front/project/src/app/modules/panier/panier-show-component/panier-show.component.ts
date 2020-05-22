import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticleDto } from '../../../model/articleDto';
import { PanierDto } from '../../../model/panierDto';
import { User } from '../../../model/user';
import { ArticleService } from '../../../service/article.service';
import { PanierService } from '../../../service/panier.service';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';
import { RoleDto } from '../../../model/roleDto';


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
  idPanier: number;
  user: String;
  role: RoleDto;
  isClient: boolean;
  isConnected: boolean;
  date = new Date ();

  constructor(private articleService: ArticleService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService, private userService: UserService,
  ) { this.userDto = new User(); this.panierDto = new PanierDto(); }


  ngOnInit() {
    this.isConnected = this.authService.isConnected();
    if (this.authService.getCurrentUser()) {
      this.isClient = this.authService.getCurrentUser().role.label === 'CLIENT';
      this.user = this.authService.getCurrentUser().nom;
      this.role = this.authService.getCurrentUser().role;
    }
    this.reload();
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
        this.articleService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )

  }

  cancel(id: number) {
    this.id = this.route.snapshot.params['id'];
    this.panierService.deleteArticles(id).subscribe(
      res => {
        this.articleService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
  }

  delete(id: number) {
    this.id = this.route.snapshot.params['id'];
    this.articleService.delete(id).subscribe(
      res => {
        this.articleService.subjectMiseAJour.next(0);
        this.reload()
      }
    )
  }

  goHome() {

    this.router.navigateByUrl('/public')

  }



}
