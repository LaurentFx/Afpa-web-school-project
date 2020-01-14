import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';

@Component({
  selector: 'app-panier-add',
  templateUrl: './panier-add.component.html',
  styleUrls: ['./panier-add.component.css']
})
export class PanierAddComponent implements OnInit {
  [x: string]: any;

  panier: PanierDto;

  constructor(private typeSalleService: PanierService, private router: Router) { }

  ngOnInit() {
    this.panier = new PanierDto();
  }

  add(): void {
    this.panierService.add(this.panier).subscribe(
      res => {
        this.panierService.subjectMiseAJour.next(0);
        console.log("Ajout Ok ");
        this.goHome();
      }
    );
    this.panier = new PanierDto();
  }

  goHome() {
    this.router.navigate(['/panier-list']);

  }

}
