import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticleDto } from '../../../model/articleDto';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { PanierService } from '../../../service/panier.service';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';
import { faHome, faCalendarPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-panier-add',
  templateUrl: './panier-add.component.html',
  styleUrls: ['./panier-add.component.css']
})
export class PanierAddComponent implements OnInit {
  article: ArticleDto;
  panierDto: PanierDto;
  userDto: User;
  manifestationDto: ManifestationDto;
  quantite: number;
  user: User;
  idUser: number;
  idManif: number;
  faHome = faHome;
  faCalendarPlus = faCalendarPlus;

  // dateValidation: Date;

  constructor(private manifestationService: ManifestationService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService,
    private userService: UserService, ) {
    this.manifestationDto = new ManifestationDto();
    this.userDto = new User();
    this.panierDto = new PanierDto();
    this.article = new ArticleDto();
  }

  ngOnInit() {
    this.reload();

  }

  reload() {
    let idUser = this.authService.getCurrentUser().id;

    this.userService.getOne(idUser).subscribe(
      res => {
        this.panierDto = res.panier;
        this.userDto = res;
      }
    );

    this.manifestationService.getOne(this.route.snapshot.params['id']).subscribe(
      resu => {
        this.manifestationDto = resu;
      }
    )
  }

  add(): void {
    this.reload();
    this.article.manifestation = this.manifestationDto;
    this.article.panier = this.panierDto;
    this.article.quantite = this.quantite;

    this.panierService.add(this.article).subscribe(
      res => {
        this.panierService.subjectMiseAJour.next(0);
        this.goHome(this.panierDto.id);
      }
    );

  }

  goHome(id: number) {
    this.router.navigateByUrl('/panier-show/' + id)
  }

}
