import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';
import { ManifestationDto } from '../../../model/manifestationDto';


@Component({
  selector: 'app-panier-show',
  templateUrl: './panier-show.component.html',
  styleUrls: ['./panier-show.component.css']
})
export class PanierShowComponent implements OnInit {

  panier: PanierDto;
  

  constructor(private route: ActivatedRoute, private panierService: PanierService, private router: Router) { }
  
  ngOnInit() {
    this.panier = new PanierDto();
    this.panier.manifestation = new ManifestationDto ();

    let id = this.route.snapshot.params['id'];

    this.panierService.getOne(id).subscribe(
      res => {
        this.panier = res;
        console.log(res);
      }
    );
  }

}

