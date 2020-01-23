import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommandeDto } from '../../../model/commandeDto';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';
import { User } from '../../../model/user';
import { CommandeService } from '../../../service/commande.service';
import { PanierService } from '../../../service/panier.service';
import { ManifestationService } from '../../../service/manifestation.service';
import { AuthService } from '../../../service/auth.service';

@Component({
  selector: 'app-commande-add',
  templateUrl: './commande-add.component.html',
  styleUrls: ['./commande-add.component.css']
})
export class CommandeAddComponent implements OnInit {
  commande: CommandeDto;
  manifestation: ManifestationDto;
  panier: PanierDto;
  quantite: number;
  user: User;
  idUser: number;
  idManif: number;
  tmp: CommandeDto;

  constructor(private manifestationService: ManifestationService, private commandeService: CommandeService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService,
  ) { this.commande = new CommandeDto(); this.panier = new PanierDto();this.manifestation = new ManifestationDto (); }

  ngOnInit() {
    this.idUser = this.authService.getCurrentUser().id;
    this.user = this.authService.getCurrentUser();
    this.commandeService.getUser(this.idUser).subscribe(
      res => {
        this.panier = res;
      }
    );

    this.idManif = this.route.snapshot.params['id'];
    this.manifestationService.getOne(this.idManif).subscribe(
      res => {
        this.manifestation = res;
      }
    );

    console.log("IdUser " + this.idUser);
    console.log("IdPanier " + this.panier.id);
    console.log("IdManif " + this.manifestation.id);

  }

  add(): void {
    this.tmp = new CommandeDto();

    console.log("IdUser " + this.idUser);
    console.log("IdPanier " + this.panier.id);
    console.log("IdManif " + this.idManif);
    this.tmp.manifestation = this.manifestation;
    this.tmp.quantite = this.quantite;
    this.tmp.panier = this.panier;

    this.commandeService.add(this.tmp).subscribe(
      res => {
        this.commandeService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
   
  }




  goHome() {

    this.router.navigate(['/commande-list']);

  }



}
