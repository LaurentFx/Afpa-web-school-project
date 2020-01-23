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

  constructor(private manifestationService: ManifestationService, private commandeService: CommandeService,
    private panierService: PanierService, private route: ActivatedRoute,
    private router: Router, private authService: AuthService,
  ) { }

  ngOnInit() {
    this.commande = new CommandeDto();
    this.commande.manifestation = new ManifestationDto();
    this.commande.panier = new PanierDto();

    let idUser = this.authService.getCurrentUser().id;
    this.panierService.getUser(idUser).subscribe(
      res => {
        this.commande.panier = res;
      }
    );

    let id = this.route.snapshot.params['id'];
    this.manifestationService.getOne(id).subscribe(
      res => {
        this.commande.manifestation = res;
      }
    );

    this.quantite = this.commande.quantite;


  }

  add(): void {
    this.commandeService.add(this.commande).subscribe(
      res => {
        this.commandeService.subjectMiseAJour.next(0);
        this.goHome();
      }
    );
    this.commande = new CommandeDto();
    this.commande.manifestation = new ManifestationDto();
    this.commande.panier = new PanierDto();
  }




  goHome() {

    this.router.navigate(['/commande-list']);

  }



}
