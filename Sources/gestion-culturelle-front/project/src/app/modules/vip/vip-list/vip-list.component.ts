import { Component, OnInit } from '@angular/core';
import { VipService } from '../../../service/vip.service';
import { Router } from '@angular/router';
import { VipDto } from '../../../model/vip-dto';

@Component({
  selector: 'app-vip-list',
  templateUrl: './vip-list.component.html',
  styleUrls: ['./vip-list.component.css']
})
export class VipListComponent implements OnInit {

  vips: VipDto[];
  isVip: boolean;

  constructor(private vipService: VipService, private router: Router) { }

  ngOnInit() {

    this.vipService.subjectMiseAJour.subscribe(
      res => {
        this.vipService.getAll().subscribe(
          donnees => {
            this.vips = donnees;
          }
        );
      }
    );

    this.vipService.getAll().subscribe(
      resultat => {
        this.vips = resultat;
      }
    );
  }

  delete(id: number) {
    this.vipService.delete(id).subscribe(
      res => {
        this.vipService.subjectMiseAJour.next(0)
        console.log('delete ok');
      }
    )
  }

  redirectToUpdate(id: number) {
    this.router.navigateByUrl('/vip-update/' + id)
  }

  redirectToShow(id: number) {
    this.router.navigateByUrl('/vip-show/' + id)
  }

}
