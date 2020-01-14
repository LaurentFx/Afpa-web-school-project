import { Component, OnInit } from '@angular/core';
import { PanierService } from '../../../service/panier.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PanierDto } from '../../../model/panierDto';

@Component({
  selector: 'app-panier-update',
  templateUrl: './panier-update.component.html',
  styleUrls: ['./panier-update.component.css']
})
export class PanierUpdateComponent implements OnInit {

  id: number;
  panier: PanierDto;

  constructor(private route: ActivatedRoute, private panierService: PanierService, private router: Router) { }

  ngOnInit() {
    this.panier = new PanierDto();

    let id = this.route.snapshot.params['id'];

    this.panierService.getOne(id).subscribe(
      res => {
        this.panier = res;
      }
    );
  }

  update(): void {
    let id = this.route.snapshot.params['id'];
    this.panierService.update(id, this.panier).subscribe(
      res => {
       console.log("Modification Ok");
        this.goHome();
      }
    );
  }

  onSubmit() {
this.update();
  }
  
  goHome() {
    this.router.navigate(['/panier-list']);
  }

}

