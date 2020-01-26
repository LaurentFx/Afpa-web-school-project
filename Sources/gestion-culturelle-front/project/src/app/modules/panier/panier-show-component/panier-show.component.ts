import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommandeDto } from '../../../model/commandeDto';
import { PanierDto } from '../../../model/panierDto';
import { User } from '../../../model/user';
import { CommandeService } from '../../../service/commande.service';
import { PanierService } from '../../../service/panier.service';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-panier-show',
  templateUrl: './panier-show.component.html',
  styleUrls: ['./panier-show.component.css']
})
export class PanierShowComponent implements OnInit {

  listCommandes: CommandeDto[];
  panierDto: PanierDto;
  userDto: User;
  id: number;
  id2: number;
  idPanier: number;


  constructor(private manifestationService: ManifestationService, private commandeService: CommandeService,
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
        this.panierDto = res.panier;
        this.userDto = res;
        this.idPanier = res.panier.id;
      }
    );

    this.id = this.route.snapshot.params['id'];
    this.commandeService.getCommandes(this.id).subscribe(
      donnees => {
        this.listCommandes = donnees;

      }
    );

  }

  valid(id: number) {
    this.id = this.route.snapshot.params['id'];
    console.log('id ' + id)
    this.panierService.delete(id).subscribe(
      res => {
        this.commandeService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )

  }

cancel(id: number) {
}

delete (id: number) {
  this.id = this.route.snapshot.params['id'];
    console.log('id ' + id)
    this.commandeService.delete(id).subscribe(
      res => {
        this.commandeService.subjectMiseAJour.next(0);
        this.goHome()
      }
    )
}

  goHome() {

    this.router.navigateByUrl('/public')

  }



}
